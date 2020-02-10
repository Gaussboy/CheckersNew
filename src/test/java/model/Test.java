package model;

public class Test {

    @org.junit.Test
    public void testToFailMove() {
        Game game = new Game();
        game.move(11,28);
        System.out.println(game.getBoard().toString());

    }
    @org.junit.Test
    public void testToTrueMove() {
        Game game = new Game();
        game.move(11,15);
        System.out.println(game.getBoard().toString());
    }
    @org.junit.Test
    public void testToRestart() {
        Game game = new Game();
        game.move(11,15);
        game.restart();
        System.out.println(game.getBoard().toString());
    }
}