package com.nbfc.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;





public class TestClass {
	public static void main(String[] args) {


		
						String subject = "Returning of claim from checker after wrong approval by checker";

						String mailBody = "Dear User, \n \n Sorry,\n Your request for claim reference no : "
								+ "Saurav"
								+ "  is returned by your checker due to the reason "
								+ "Tyagi"
								+ " and "
								+ "Hello"
								+ " .\n You are requested to relodge the claim. \n \n Thanks & Regards \n CGTMSE.  ";

						System.out.println("mailBody is " + mailBody);
						try {
							String host = "192.168.10.118";
							boolean sessionDebug = false;
							Properties props = System.getProperties();
							props.put("mail.host", host);
							props.put("mail.transport.protocol", "smtp");
							props.put("mail.smtp.host", host);
							props.put("mail.from", "support@cgtmse.in");

							Session session1 = null;
							session1 = Session.getDefaultInstance(props, null);
							session1.setDebug(sessionDebug);

							javax.mail.internet.MimeMessage msg = new javax.mail.internet.MimeMessage(
									session1);
							msg.setFrom(new javax.mail.internet.InternetAddress(
									"support@cgtmse.in"));

							javax.mail.internet.InternetAddress[] Toaddress = { new javax.mail.internet.InternetAddress(
									"support@cgtmse.in") };
							msg.setRecipients(
									javax.mail.Message.RecipientType.TO,
									Toaddress);
							msg.setSubject(subject);
							msg.setSentDate(new Date());

							msg.setText(mailBody);

							System.out.println("BEFORE SEND MAIL" + "support@cgtmse.in");
							Transport.send(msg);

							System.out.println("AFTER SEND MAIL" + "support@cgtmse.in");
						}

						catch (Exception e) {
							e.printStackTrace();

						}

						
	}
	}
