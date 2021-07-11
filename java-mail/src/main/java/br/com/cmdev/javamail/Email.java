package br.com.cmdev.javamail;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

@Stateless
public class Email {
	
	@Resource(lookup = "java:/jboss/mail/gmail")
	private Session sessionMail;
	
	public Email() {}	
	
	public void send(String to, String subject, String body) {
		Message message = new MimeMessage(sessionMail);
		try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			
			Transport.send(message);
			
		} catch (MessagingException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao enviar email. Você está sem conexão. "+ex.getMessage());
		}
	}
	

	
}
