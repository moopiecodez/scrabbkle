package pij.main;

public class PremiumWordSquare extends Square {

    private int multiplier;

    /**
     * PremiumWordSquares require a multiplier in the range -9 to 99.
     */
    public PremiumWordSquare(int multiplier) {
        this.setMultiplier(multiplier);
    }

    /**
     * Set the value of the word score multiplier.
     */

    private void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    /** 
     * The string representation of a PremiumWordSquare is the premium
     * word multiplier value enclosed in two curly brackets.
     */
    public String toString() {
        String string;
        if(getTile() != null){
            string =  String.format("%-3s", getTile());
        } else {
            string = squareDetailToString(this.multiplier, "{", "}");
        }
        return string;
    }

    public int getMultiplier() {
        return this.multiplier;
    }
}
