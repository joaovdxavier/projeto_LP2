package exceptions;

public class LocDuplicadaException extends Exception{
	private String message = "N�o pode haver duas localiza��es ou mais com o mesmo nome!";
	
	public String getMessage() {
		return message;
	}
}