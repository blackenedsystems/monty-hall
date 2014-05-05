package com.blackenedsystems.montyhall;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alan Tibbetts
 * @since 3/5/14 12:38
 */
public class GameTest {

    @Test
    public void gameInitialisedOk() {
        Game game = new Game();
        assertNotNull("Game", game);
        assertEquals("Number of Boxes", 3, game.numberOfBoxes());
        assertTrue("One and only one winner", game.hasOneWinner());
    }

    @Test
    public void onlyOneWinner() {
        Game game = new Game();
        assertEquals("Initial number of boxes", 3, game.numberOfBoxes());

        Player player = new Player();
        player.makeSelection(game);
        assertEquals("Number of boxes after player selection", 2, game.numberOfBoxes());

        Host host = new Host();
        host.openBox(game);
        assertEquals("Number of boxes after host selection", 1, game.numberOfBoxes());

        int numberOfWinners = 0;
        if (player.getPrizeBox() == PrizeBox.WINNER) {
            numberOfWinners++;
        }
        if (host.getOpenedBox() == PrizeBox.WINNER) {
            numberOfWinners++;
        }
        if (game.getUnselectedBox() == PrizeBox.WINNER) {
            numberOfWinners++;
        }
        assertEquals("Number of winning boxes", 1, numberOfWinners);
    }
}
