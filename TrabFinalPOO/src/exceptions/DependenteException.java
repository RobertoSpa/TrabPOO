package exceptions;

public class DependenteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DependenteException(String message) {
		super(message);
	}
}
