package pij.main;

public class Human extends Player {

    private static final String TILE_MSG = "It's your turn! Your tiles:";

    public Human(Rack rack) {
        super(rack);
    }

    public void getMove() {
       System.out.println(TILE_MSG);
       System.out.println(this.rack.toString());
    }
}
