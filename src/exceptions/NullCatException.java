package exceptions;

public class NullCatException extends Exception {

	private String message = "Não existe essa categoria!";
	
	public String getMessage() {
		return message;
	}
}