package exceptions;

public class BemDuplicadoException extends NullPointerException{
	private String message = "Não pode haver dois bens ou mais com o mesmo código!";
	
	public String getMessage() {
		return message;
	}
}