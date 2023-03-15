package pij.main;

public class StandardSquare extends Square {

    /**
     * The string representation of a StandardSquare is a dot enclosed in two 
     * spaces.
     */
    public String toString() {
        String string;
        if( getTile() != null) {
            string = getTile().toString();
        } else {
            string = " . ";
        }
        return string;
    }

    protected int getMultiplier() {
        throw new UnsupportedOperationException(
                "StandardSquares do not have a multiplier");
    }
}
