package exceptions;

public class BemDuplicadoException extends NullPointerException{
	private String message = "N�o pode haver dois bens ou mais com o mesmo c�digo!";
	
	public String getMessage() {
		return message;
	}
}