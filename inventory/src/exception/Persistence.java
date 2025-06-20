package exception;

public class Persistence extends Exception{
	/**
	 * This is specifically for getting rid of the warning flag on this class
	 */
	private static final long serialVersionUID = 1L;

	public Persistence(String message, Throwable cause) {
        super(message, cause);
    }
    
    public Persistence(String message) {
        super(message);
    }
}
