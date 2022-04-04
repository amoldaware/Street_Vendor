package com.nbfc.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.nbfc.model.MailConfiguration;
@Repository("emailUtility")
public class EmailUtility {
	private static String MAIL_FROM = null;
	private static String MAIL_FROM_NAME = null;
	@Autowired
	private MailConfiguration mailConf;
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		Session session = null;
		String mailSmtpHost = null;
		String mailSmtpPort = null;
		String email = null;
		String passwd = null;
		try {
			/* Get All instances */
			//MailConfiguration mailConf = new MailConfiguration();
			org.hibernate.Session s = sessionFactory.getCurrentSession();
			//mailConf=(MailConfiguration)sessionFactory.getCurrentSession();
			/* get details */
			if (mailConf != null) {
				System.out.println("mailSmtpHost::"+mailConf.getSmtpHost());
				mailSmtpHost = mailConf.getSmtpHost();
				mailSmtpPort = mailConf.getSmtpDomain();
				email = mailConf.getEmailId();
				passwd = mailConf.getPassword();
				MAIL_FROM_NAME = mailConf.getEmailIdFromName();
				MAIL_FROM = email;
			} else {
				System.out.println("Error");
				throw new Exception("Mail Configuration not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (email != null && passwd != null && mailSmtpHost != null) {
			final String fromEmail = email; // requires valid gmail id
			final String password = passwd; // correct password for gmail id
			Properties props = new Properties();
			props.put("mail.smtp.host", mailSmtpHost); // SMTP Host
			props.put("mail.smtp.port", mailSmtpPort); // TLS Port
			props.put("mail.smtp.auth", "true"); // enable authentication
			props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
			if (mailSmtpHost.contains("gmail.com") && mailSmtpPort.equals("465")) {
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}
			// create Authenticator object to pass in Session.getInstance
			// argument
			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			session = Session.getInstance(props, auth);
		}
		return session;
	}

	public void sendEmail(String mailTo, String mailCC, String mailSubject, String mailBody) {
		try {
			Session session = new EmailUtility().getSession();
			if (session != null) {
				BodyPart messageBodyPart = null;
				MimeMultipart multipart = null;
				MimeMessage msg = null;
				msg=new MimeMessage(session);
				/* Setting Mails */
				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				msg.addHeader("format", "flowed");
				msg.addHeader("Content-Transfer-Encoding", "8bit");
				msg.setFrom(new InternetAddress(MAIL_FROM, MAIL_FROM_NAME));
				msg.setReplyTo(InternetAddress.parse(MAIL_FROM, false));
				msg.setSentDate(new Date());
				messageBodyPart = new MimeBodyPart();
				multipart = new MimeMultipart();
				String mailMsg = mailBody;
				/* Adding Message and Company Logos */
				mailMsg = mailMsg + "<BR><BR>*** This is an automatically generated email, please do not reply *<BR><img src=\"cid:image\" height='10%' width='10%'>";
				messageBodyPart.setContent(mailMsg, "text/html");
				multipart.addBodyPart(messageBodyPart);
				msg.setRecipients(Message.RecipientType.TO, mailTo);
				msg.setContent(multipart);
				/* Setting Mail Subject and Recipients */
				msg.setSubject(mailSubject, "UTF-8");
				Transport.send(msg);
			}else {
				System.out.println("No Session");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
