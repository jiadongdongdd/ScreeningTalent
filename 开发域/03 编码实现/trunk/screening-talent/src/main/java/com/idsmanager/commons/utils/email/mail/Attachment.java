package com.idsmanager.commons.utils.email.mail;

/**
 * @author Zhang Hui
 */
public class Attachment {
    private String name;
    private byte[] bytes;

    public Attachment(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
