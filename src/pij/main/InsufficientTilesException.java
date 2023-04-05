package pij.main;

/**
 * Class creating a new exception for when there aren't enough Tiles. Extends
 * RuntimeException.
 * @author Maurane van der Stoep
 *
 */
public class InsufficientTilesException extends RuntimeException {
	/**
	 * Generates a new exception including a message for when there are no more
	 * Tiles.
	 */
    public InsufficientTilesException() {
		super("There are no more tiles!");
	}
}
