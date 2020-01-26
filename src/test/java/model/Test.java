package model;

public class Test {

    @org.junit.Test
    public void testToFail() {
        Game game = new Game();

        System.out.println(game.move(12, 20));

    }
}
