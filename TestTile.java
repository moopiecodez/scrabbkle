class TestTile {
    public static void main(String[] args) {
        Tile s = new Tile();
        s.letter = 'S';
        s.score = 1;
        System.out.println(s.showTile());
        System.out.println(s.getLetter());
        System.out.println(s.getScore());
    }
}
