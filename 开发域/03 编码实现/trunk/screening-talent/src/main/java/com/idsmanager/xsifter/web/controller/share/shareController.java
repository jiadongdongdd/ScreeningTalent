package com.idsmanager.xsifter.web.controller.share;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.WriterException;
import com.idsmanager.xsifter.infrastructure.Share;
import com.idsmanager.xsifter.service.WxOrderService;
import com.idsmanager.xsifter.service.WxPayService;
import com.idsmanager.xsifter.service.dto.wx.pay.WxQrCodeDto;

/**
 * 分享控制层
 *类名称
 *创建人 dushaofei
 *创建时间：2016-2-15 上午10:37:31 
 *类描述：
 *@version
 */
@Controller
public class shareController {
	
	@Autowired
	private WxPayService wxPayService;

	@Autowired
	private WxOrderService wxOrderService;
	/**
	 * 分享qq空间
	 * thinkpad dushaofei
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/share/qzone",method=RequestMethod.GET)
	@ResponseBody
	public  ProcessResult<String>  qzone(){
		ProcessResult<String> pr = new ProcessResult<>();
//		response.setContentType("text/xml;charset=utf-8");   
//		response.setHeader("Cache-Control", "no-cache");   
		String url = Share.getQzoneUrl();
		pr.setDetail(url);
		return pr;
	}
	/**
	 * 分享sina微博
	 * thinkpad dushaofei
	 * @return
	 */
	@RequestMapping(value="/share/sina")
	@ResponseBody
	public ProcessResult<String> sina(){
		ProcessResult<String> pr = new ProcessResult<>();
		String url = Share.getSinaUrl();
		pr.setDetail(url);
		return pr;
	}
	
	/**
	 * 分享微信
	 * thinkpad dushaofei
	 * @return
	 */
	@RequestMapping(value="/share/wechant")
	@ResponseBody
	public ModelAndView wechant(){
		WxQrCodeDto dto = new WxQrCodeDto();
		return new ModelAndView("news/product_qrcode");
	}
	
	/**
	 * 生成支付二维码
	 * 
	 * @param response
	 */
	@RequestMapping(value = "share/payQR", method = RequestMethod.GET)
	@ResponseBody
	public void getQR(
			HttpServletResponse response) {
		 
	}
}
