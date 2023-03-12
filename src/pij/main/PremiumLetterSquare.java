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
        String string = "(" + this.multiplier;;
        if(multiplier >= 0 && multiplier <= 9) {
            string += ")";
        }
        return string;
    }
}
