package exception;

public class NullBemCatException extends Exception {

	private String message = "N�o existe essa categoria!";
	
	public String getMessage() {
		return message;
	}
}
