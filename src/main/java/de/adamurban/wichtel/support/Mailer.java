package de.adamurban.wichtel.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {
	
	// Properties
	private static final String SMTP_HOST_NAME = "smtp_host_name";
    private static final String SMTP_AUTH_USER = "smtp_auth_user";
    private static final String SMTP_AUTH_PWD  = "smtp_auth_pwd";
	
	private final Properties prop = new Properties();

	public Mailer(final String propertyFile) throws IOException {
		InputStream input = new FileInputStream(propertyFile);
		prop.load(input);
	}
	
	public void send(final String to, final String subject, final String text) throws Exception{
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", prop.getProperty(SMTP_HOST_NAME));
        props.put("mail.smtp.auth", "true");
        
        System.out.println(to);

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(subject);
        message.setContent(text, "text/plain");
        message.setFrom(new InternetAddress(prop.getProperty(SMTP_AUTH_USER)));
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress(to));

        transport.connect();
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = prop.getProperty(SMTP_AUTH_USER);
           String password = prop.getProperty(SMTP_AUTH_PWD);
           return new PasswordAuthentication(username, password);
        }
    }

}
