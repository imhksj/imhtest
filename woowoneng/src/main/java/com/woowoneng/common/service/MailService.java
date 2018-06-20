package com.woowoneng.common.service;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MailService{
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
    private JavaMailSender mailSender;

	@Value("#{global['smtp.host']}")
	private String SMTP_HOST;

	@Value("#{global['smtp.fromUser']}")
	private String FROM_USER;
	
	@Value("#{global['smtp.toUser']}")
	private String TO_USER;
	
	@Transactional
	public boolean sendMail(Map<String,Object> map) {
		
		Properties p = System.getProperties();
		p.setProperty("mail.smtp.host", SMTP_HOST);
		
		Session session = Session.getDefaultInstance(p);
		
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject(map.get("subject").toString());
            String[] arrayUser = TO_USER.split(";");
            // 수신인 다수
            String[] toArrayResult = new String[arrayUser.length];
            for(int i=0; i<arrayUser.length; i++){
            	toArrayResult[i] =  arrayUser[i];
            }
            messageHelper.setTo(toArrayResult);
            messageHelper.setFrom(FROM_USER, "WOOWONENG Mail Sending System");
            messageHelper.setText(map.get("mailContent").toString(), true);

            logger.debug("Send Mail Subject : {}", map.get("subject").toString());
            
            Transport.send(message);
            
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
             return false;
        } catch (Exception ex) {
        	ex.printStackTrace();
              	 return false;
        }
	}

}
