package exception;

public class BemDuplicadoException extends NullPointerException{
	private String message = "N�o pode haver dois itens ou mais com o mesmo nome!";
	
	public String getMessage() {
		return message;
	}
}
