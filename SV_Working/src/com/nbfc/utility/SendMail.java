package com.nbfc.utility;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class SendMail {
	
	private static String MAIL_FROM = null;
	private static String MAIL_FROM_NAME = null;
	private static final Logger logger=Logger.getLogger(SendMail.class);
	/**
	 * getSession methods provide the mail send session
	 * 
	 * @return
	 */
	private Session getSession()
	{
		Session session = null;
		String mailSmtpHost = null;
		String mailSmtpPort = null;
		String email = null;
		String passwd = null;
		try
		{
				mailSmtpHost = "smtp.office365.com";
				mailSmtpPort = "587";
				email = "registration@cgtmse.in";
				passwd = "cgt$2020";
				MAIL_FROM_NAME = "Street Vendor Scheme";
				MAIL_FROM = email;
		}
		catch (Exception e)
		{
			logger.error(e);
			e.printStackTrace();
		}
		if (email != null && passwd != null && mailSmtpHost != null)
		{
			final String fromEmail = email; // requires valid gmail id
			final String password = passwd; // correct password for gmail id
			Properties props = new Properties();
			props.put("mail.smtp.host", mailSmtpHost); // SMTP Host
			props.put("mail.smtp.port", mailSmtpPort); // TLS Port
			props.put("mail.smtp.auth", "true"); // enable authentication
			props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
			//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			if (mailSmtpHost.contains("gmail.com") && mailSmtpPort.equals("465"))
			{
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}
			// create Authenticator object to pass in Session.getInstance
			// argument
			javax.mail.Authenticator auth = new javax.mail.Authenticator() 
			{
				// override the getPasswordAuthentication method
				protected javax.mail.PasswordAuthentication getPasswordAuthentication()
				{
					return new javax.mail.PasswordAuthentication(fromEmail, password);
				}
			};
			session = Session.getInstance(props, auth);
		}
		return session;
	}

	public static void sendEmail(String mailTo, String mailSubject, String mailBody)
	{
		try
		{
			Session session = new SendMail().getSession();
			if (session != null)
			{
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
				String mailMsg = "<BR>";
				/* Adding Message and Company Logos */
				mailMsg = mailBody + "<BR><BR><Font color=red><b>Note : This is an auto-generated mail, please do not to respond to this mail.<b></font>"+
				"<BR><b>If you have any scheme related query, please write to querysvs@cgtmse.in"+
				"<BR>If you have any IT related query, please write to itsupportsvs@cgtmse.in</b><BR>";
				messageBodyPart.setContent(mailMsg, "text/html");
				multipart.addBodyPart(messageBodyPart);
				msg.setRecipients(Message.RecipientType.TO, mailTo);
				msg.setContent(multipart);
				/* Setting Mail Subject and Recipients */
				msg.setSubject(mailSubject, "UTF-8");
				Transport.send(msg);
			}else {
				System.out.println("session is null");
			}
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
