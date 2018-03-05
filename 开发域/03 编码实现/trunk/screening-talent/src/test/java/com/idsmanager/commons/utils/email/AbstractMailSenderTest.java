package com.idsmanager.commons.utils.email;

import com.idsmanager.commons.utils.email.mail.Attachment;
import com.idsmanager.commons.utils.email.mail.Email;
import com.idsmanager.commons.utils.email.mail.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Zhang Hui
 */
@ContextConfiguration(locations = {"classpath:spring/xsifter.xml"})
public class AbstractMailSenderTest  extends AbstractTestNGSpringContextTests{

    @Autowired
    private MailGunSender mailSender;

    @Test(enabled = false)
    public void testSendMail() throws Exception {


        mailSender.sendMail(new Email() {
            @Override
            public List<Mailer> receipts() {
                return Collections.singletonList(new Mailer("zhao", "zhao@idsmanager.com"));
            }

            @Override
            public String subject() {
                return "九州云腾测试邮件";
            }

            @Override
            public String content() {
                return "你好，九州云腾";
            }

        });

        Thread.sleep(5000);

    }
}