package com.idsmanager.commons.utils.email;

import com.idsmanager.commons.utils.email.mail.Email;
import com.idsmanager.commons.utils.email.mail.Mailer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.Collections;
import java.util.List;

/**
 * @author Zhang Hui
 */
public abstract class AbstractMailSender {
    private Mailer testReceipt = new Mailer("dushaofeiTest", "fei@idsmanager.com");

    private String test = PropEmailUserInfo.getUserInfo("test");
    protected String fromName = "筛子网";
    protected String fromEmail = "xsifter@idsmanager.com";


    public void sendMail(final Email email) {
        final List<Mailer> receipts;
        if (Boolean.parseBoolean(test)) {
            receipts = Collections.singletonList(testReceipt);
        } else {
            receipts = email.receipts();
        }

        try {
            doSend(email, receipts);
        } catch (Exception e) {
            throw new IllegalStateException("Error happens when sending the email!", e);
        }
    }

    protected abstract void doSend(Email email, List<Mailer> receipts);

}
