package pij.main;

public class InsufficientTilesException extends RuntimeException {
	public InsufficientTilesException() {
		super("There are no more tiles!"); //calls constructor of parent class (RunTimeException) which takes a string
	}
}
