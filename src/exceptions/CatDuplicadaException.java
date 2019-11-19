package exceptions;

public class CatDuplicadaException extends Exception{
	private String message = "Não pode haver duas categorias ou mais com o mesmo código!";
	
	public String getMessage() {
		return message;
	}
}
