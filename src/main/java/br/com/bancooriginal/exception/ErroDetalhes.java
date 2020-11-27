package br.com.bancooriginal.exception;

/**
 * 
 * @author Cosme Ribeiro da Silva
 *
 */
public class ErroDetalhes {
	private String mensagem;
	private String detalhes;

	public ErroDetalhes(String mensagem, String detalhes) {
		super();
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
}
