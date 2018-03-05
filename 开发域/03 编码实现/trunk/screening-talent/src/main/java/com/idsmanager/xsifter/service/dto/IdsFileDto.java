package com.idsmanager.xsifter.service.dto;


import com.idsmanager.commons.file.ImageUtils;
import com.idsmanager.xsifter.domain.commons.IdsFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author Shengzhao Li
 */
public class IdsFileDto extends AbstractDto {

    private static final long serialVersionUID = -8839211666849252736L;

    protected String guid;
    protected String name;
    protected byte[] data;
    protected long size;


    public IdsFileDto() {
    }

    public IdsFileDto(IdsFile file) {
        this(file, false);
    }

    public IdsFileDto(IdsFile file, boolean includeData) {
        this.guid = file.getGuid();
        this.name = file.name();
        this.size = file.size();

        if (includeData) {
            this.data = file.data();
        }
    }

    public String downloadFileName() throws UnsupportedEncodingException {
        String fileName = StringUtils.trimAllWhitespace(this.name);
        return new String(fileName.getBytes(), "ISO8859-1");
    }

    public String getContextTypeExtension() {
        final String extension = FilenameUtils.getExtension(this.name).toLowerCase();
        if ("png".equals(extension) || "gif".equals(extension)) {
            return extension;
        }
        return ImageUtils.DEFAULT_IMAGE_TYPE;
    }


    public long getSize() {
        return size;
    }

    public long getSizeAsKB() {
        return (size / 1024);
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}