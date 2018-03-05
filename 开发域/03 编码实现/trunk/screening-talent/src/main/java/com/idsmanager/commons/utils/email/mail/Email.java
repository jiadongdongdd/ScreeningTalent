package com.idsmanager.commons.utils.email.mail;

import java.util.Collections;
import java.util.List;

/**
 * @author Zhang Hui
 */
public abstract class Email {

    public abstract List<Mailer> receipts();

    public abstract String subject();

    public abstract String content();

    public List<Attachment> attachments() {
        return Collections.emptyList();
    }

    public List<Attachment> inlineAttachments() {
        return Collections.emptyList();
    }

}
