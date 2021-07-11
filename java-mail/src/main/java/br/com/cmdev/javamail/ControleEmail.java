package br.com.cmdev.javamail;

import java.io.UnsupportedEncodingException;
import java.security.Provider;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class ControleEmail {
	
	private static Logger log = Logger.getLogger(ControleEmail.class.getName());

	public static void main(String[] args) {
		new ControleEmail().envioSimples("CMDEV - Desevolvimento", // Remetente
				"Teste JavaMail", // Assunto
				"Oi, tudo bem? Isso é apenas um testes do app desenvolvido utilizando o JavaMail", // Mensagem
				"calixto.macedo@gmail.com"); // Email do Destinat�rio (O EMAIL PRECISA SER VALIDO)
	}

	public void envioSimples(String nomeRemetente, String assunto, String mensagem, String destinatario) {
		try {
			ModeloEmail me = new ModeloEmail();
			String host = "smtp.gmail.com";
			String usuario = "cmdev.desenv@gmail.com";
			String senha = "cmdevD@2021";
			String remetente = "cmdev.desenv@gmail.com"; // Pode trocar aqui pela String usuario
			me.setAssunto(assunto);
			me.setMensagem(mensagem);
			boolean sessionDebug = true;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			
			java.security.Security.addProvider(new Provider(host, remetente, senha) {
				private static final long serialVersionUID = -6109223029603537960L;
			});
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(remetente, nomeRemetente));
			msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			msg.setSubject(assunto);
			msg.setSentDate(new Date());
			msg.setContent(mensagem, "text/html;charset=UTF-8");

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, usuario, senha);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

			System.out.println("Enviado com Sucesso");
		} catch (MessagingException ex) {
			log.severe("Erro:"+ ex);
			JOptionPane.showMessageDialog(null, "Erro ao enviar email. Você está sem conexão. "+ex.getMessage());
		} catch (UnsupportedEncodingException ex) {
			JOptionPane.showMessageDialog(null, "Destinatário Inválido.");
		}
	}

}
