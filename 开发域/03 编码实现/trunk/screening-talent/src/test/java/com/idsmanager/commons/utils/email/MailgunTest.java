/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.idsmanager.commons.utils.email;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;

/**
 * 2016/2/23
 *
 * @author Shengzhao Li
 */
public class MailgunTest {


    @Test(enabled = false)
    public void test() throws Exception {

        final ClientResponse clientResponse = sendSimpleMessage();
        final int status = clientResponse.getStatus();
        System.out.println(status);
    }


    public static ClientResponse sendSimpleMessage() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-b44d71e668796ee52818b1135ba30b59"));
        WebResource webResource =
                client.resource("https://api.mailgun.net/v3/sandbox060887615d724fea8706a61aa976fb78.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox060887615d724fea8706a61aa976fb78.mailgun.org>");
        formData.add("to", "zhao <zhao@idsmanager.com>");
        formData.add("subject", "Hello zhao");
        formData.add("text", "Congratulations zhao, you just sent an email with Mailgun!  You are truly awesome!  You can see a record of this email in your logs: https://mailgun.com/cp/log .  You can send up to 300 emails/day from this sandbox server.  Next, you should add your own domain so you can send 10,000 emails/month for free.");
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }

}
