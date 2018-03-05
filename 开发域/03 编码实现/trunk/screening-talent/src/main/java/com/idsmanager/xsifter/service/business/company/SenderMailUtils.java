package com.idsmanager.xsifter.service.business.company;

import java.util.Collections;
import java.util.List;


import com.idsmanager.commons.utils.email.MailGunSender;
import com.idsmanager.commons.utils.email.mail.Email;
import com.idsmanager.commons.utils.email.mail.Mailer;

/**
 * 发送企业邮件 类名称 创建人 dushaofei 创建时间：2016-1-28 下午6:09:03 类描述：
 * 
 * @version
 */
public class SenderMailUtils {
	
	/**
	 * 
	 * thinkpad dushaofei
	 * 
	 * @return
	 */
	public synchronized static Boolean send(final SenderInfoUtils infoUtils) {
		MailGunSender mailSender = new MailGunSender();
		Boolean when = true;
        try {
        	mailSender.sendMail(new Email() {
                @Override
                public List<Mailer> receipts() {
                    return Collections.singletonList(new Mailer(infoUtils.getUsername(), infoUtils.getEmail()));
                }

                @Override
                public String subject() {
                    return infoUtils.getSubject();
                }

                @Override
                public String content() {
                	
                	 return "你好:"+infoUtils.getUsername()+infoUtils.getContext();
                }

            });
		} catch (Exception e) {
			e.printStackTrace();
			when = false;
		}
		return when;
	}
}
