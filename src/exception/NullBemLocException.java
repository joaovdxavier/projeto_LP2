package exception;

public class NullBemLocException extends Exception{
	
	private String message = "N�o existe essa localizacao!";
	
	public String getMessage() {
		return message;
	}
}
