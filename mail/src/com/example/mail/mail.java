package com.example.mail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail{
	//for gmail,use:smtp.gmail.com
	//for yahoo,use:smtp.mail.yahoo.com
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	//for gmail,use:SSL Port 465
	//for yahoo,use:SSL Port 465
	//for
	private static final String SMTP_PORT = "465";
	private static final String emailMsgTxt = "Test Message Contents";
	private static final String emailSubjectTxt = "A test from gmail";
	private static final String emailFromAddress = "****@gmail.com";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static final String[] sendTo = {"&&&&&.&&&&&&@yahoo.com"};

	public static void main(String args[]) throws Exception {

		new mail().sendSSLMessage(sendTo, emailSubjectTxt,
				emailMsgTxt, emailFromAddress);
		System.out.println("Sucessfully Sent mail to All Users");
	}

	public void sendSSLMessage(String recipients[], String subject,
			String message, String from) throws MessagingException {
		boolean debug = true;

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("******@gmail.com", "**********");
			}
		});

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
}