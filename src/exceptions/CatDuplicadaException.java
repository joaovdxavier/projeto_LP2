package exceptions;

public class CatDuplicadaException extends Exception{
	private String message = "N�o pode haver duas categorias ou mais com o mesmo c�digo!";
	
	public String getMessage() {
		return message;
	}
}
