package pij.main;

public class PremiumWordSquare extends Square {

    /**
     * PremiumWordSquares require a multiplier in the range -9 to 99.
     */
    public PremiumWordSquare(int wordMultiplier) {
        super(1, wordMultiplier);
        LetterScoring letterScoring = new StandardLetterScoring();
        setLetterScoring(letterScoring);
        WordScoring wordScoring = new PremiumWordScoring();
        setWordScoring(wordScoring);
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
            string = squareDetailToString(getWordMultiplier(), "{", "}");
        }
        return string;
    }

}
