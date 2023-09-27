package jsoft.library;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utilities_mailer {
	
	public static boolean sendMail(String to, String subject, String body) {
			
		String username = "adsystemsenderlt223@gmail.com";
		String userpass = "ooiejzghzncwppis";
		String host = "smtp.gmail.com";
		String port = "587";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, userpass);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			return true;
		}catch(AddressException e) {
			e.printStackTrace();
		}catch (MessagingException e1) {
			e1.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		return false;
	}
	
	public static boolean PasswordMailer(String to, String name, String pass) {
		String subject = "Thông tin đổi mật khẩu";
		String body = "Mật khẩu tài khoản của bạn("+ name +") đã được đổi thành: " + pass + " - Vui lòng không cung cấp thông tin đăng nhập cho bất kì ai.";
		return sendMail(to, subject, body);
	}
	
}
