package exceptions;

public class NullLocException extends Exception{
	
	private String message = "N�o existe essa localiza��o!";
	
	public String getMessage() {
		return message;
	}
}