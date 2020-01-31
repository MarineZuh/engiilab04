package engii.lab4.engiilab4.exception;

public class ClienteException extends Exception{

	private String mensagem;
	
	public ClienteException(String msg) {
		this.mensagem = msg;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.mensagem;
	}
	
	
}
