package exceptions;

public class NullLocException extends Exception{
	
	private String message = "Não existe essa localização!";
	
	public String getMessage() {
		return message;
	}
}