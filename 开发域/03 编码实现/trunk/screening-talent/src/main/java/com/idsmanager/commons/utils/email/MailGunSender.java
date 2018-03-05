package com.idsmanager.commons.utils.email;

import com.idsmanager.commons.utils.email.mail.Attachment;
import com.idsmanager.commons.utils.email.mail.Email;
import com.idsmanager.commons.utils.email.mail.Mailer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.StreamDataBodyPart;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author Zhang Hui
 */
@Component
public class MailGunSender extends AbstractMailSender {

    private String host = PropEmailUserInfo.getUserInfo("host");
    private String username = PropEmailUserInfo.getUserInfo("username");
    private String password = PropEmailUserInfo.getUserInfo("password");


    private Logger log;


    public void doSend(Email email, List<Mailer> receipts) {
        FormDataMultiPart formData = new FormDataMultiPart();
        formData.field("from", fromName + "<" + fromEmail + ">");
        for (Mailer receipt : receipts) {
            formData.field("to", receipt.getName() + "<" + receipt.getEmail() + ">");
//            System.out.println(receipt.getName());
//            System.out.println(receipt.getEmail());
        }
        formData.field("subject", email.subject());
        formData.field("html", email.content());
        List<Attachment> inlineAttachments = email.inlineAttachments();
        for (Attachment attachment : inlineAttachments) {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(attachment.getBytes());
            formData.bodyPart(new StreamDataBodyPart("inline", byteInputStream, attachment.getName(), javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM_TYPE));
        }
        List<Attachment> attachments = email.attachments();
        for (Attachment attachment : attachments) {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(attachment.getBytes());
            formData.bodyPart(new StreamDataBodyPart("attachment", byteInputStream, attachment.getName(), javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM_TYPE));
        }


        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(username, password));
        WebResource webResource = client.resource(host);
        webResource.type(javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA_TYPE).post(String.class, formData);
    }


    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
