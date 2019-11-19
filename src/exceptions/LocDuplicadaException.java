package exceptions;

public class LocDuplicadaException extends Exception{
	private String message = "Não pode haver duas localizações ou mais com o mesmo nome!";
	
	public String getMessage() {
		return message;
	}
}