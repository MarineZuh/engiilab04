package engii.lab4.engiilab4.exception;

public class CidadeException extends Exception{

	private String mensagem;
	
	public CidadeException(String msg) {
		this.mensagem = msg;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.mensagem;
	}
	
	
}
