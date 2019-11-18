package exception;

public class NullBemLocException extends Exception{
	
	private String message = "Não existe essa localizacao!";
	
	public String getMessage() {
		return message;
	}
}
