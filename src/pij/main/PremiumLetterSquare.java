package pij.main;

public class PremiumLetterSquare extends Square {

    private int multiplier;

    /**
     * PremiumLetterSquares require a multiplier in the range -9 to 99.
     */
    public PremiumLetterSquare(int multiplier) {
        this.setMultiplier(multiplier);
    }

    /**
     * Set the value of the letter score multiplier.
     */

    private void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    /** 
     * The string representation of a PremiumLetterSquare is the premium
     * letter multiplier value enclosed in two round brackets.
     */
    public String toString() {
        String string;
        if(getTile() != null) {
            string = String.format("%-3s", getTile());
        } else {
            string = squareDetailToString(this.multiplier, "(", ")");
        }
        return string;
    }

    public int getMultiplier() {
        return this.multiplier;
    }
}
