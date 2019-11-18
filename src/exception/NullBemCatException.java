package exception;

public class NullBemCatException extends Exception {

	private String message = "Não existe essa categoria!";
	
	public String getMessage() {
		return message;
	}
}
