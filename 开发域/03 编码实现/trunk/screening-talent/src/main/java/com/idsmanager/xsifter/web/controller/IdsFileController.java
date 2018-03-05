package com.idsmanager.xsifter.web.controller;


import com.idsmanager.commons.file.ImageSizeAdjuster;
import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.xsifter.service.CommonService;
import com.idsmanager.xsifter.service.dto.IdsFileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/public/")
public class IdsFileController {


    private static Logger logger = LoggerFactory.getLogger(IdsFileController.class);

    @Autowired
    private CommonService fileService;


    @RequestMapping("image/{guid}")
    public void image(@PathVariable("guid") String guid, String w, HttpServletResponse response) throws Exception {
        logger.debug("Load image by guid " + guid);
        IdsFileDto fileDto = fileService.loadFileByGuid(guid);

        final String extension = fileDto.getContextTypeExtension();
        response.setContentType("image/" + extension);
        byte[] data = fileDto.getData();
        //check adjust image or not
        writeData(w, response, MatchUtils.isPositiveNumber(w), extension, data);
    }


    private void writeData(String w, HttpServletResponse response, boolean positiveNumber, String extension, byte[] data) throws IOException {
        //check adjust image or not
        if (positiveNumber) {
            ImageSizeAdjuster sizeAdjuster = new ImageSizeAdjuster(data);
            data = sizeAdjuster.adjust(Integer.valueOf(w), extension);
        }
        ServletOutputStream os = response.getOutputStream();
        os.write(data);
        os.flush();
    }



    @RequestMapping("download/{guid}")
    public void fileUpload(@PathVariable("guid") String guid, HttpServletResponse response) throws Exception {
        logger.debug("Load file by guid" + guid);
        IdsFileDto fileDto = fileService.loadFileByGuid(guid);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileDto.downloadFileName());

        ServletOutputStream out = response.getOutputStream();
        out.write(fileDto.getData());
        out.flush();
    }


}