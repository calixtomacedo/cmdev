package br.com.cmdev.javamail;

import java.io.Serializable;

public class ModeloEmail implements Serializable {

	private static final long serialVersionUID = -6564160039323445631L;

	private String assunto;
	private String mensagem;

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
