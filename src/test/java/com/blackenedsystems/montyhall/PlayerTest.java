package com.blackenedsystems.montyhall;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Alan Tibbetts
 * @since 4/5/14 16:01
 */
public class PlayerTest {

    @Test
    public void selectBox_ok() {
        Game game = new Game();
        Player player = new Player();
        player.makeSelection(game);
        assertNotNull("Player has selected box", player.getPrizeBox());
        assertEquals("Two boxes remaining", 2, game.numberOfBoxes());
    }

    @Test(expected = IllegalStateException.class)
    public void selectBox_doubleSelection() {
        Game game = new Game();
        Player player = new Player();
        player.makeSelection(game);
        player.makeSelection(game);
    }

    @Test
    public void swapBoxes_ok() {
        Game game = new Game();
        Player player = new Player();
        player.makeSelection(game);

        PrizeBox initialSelection = player.getPrizeBox();

        Host host = new Host();
        host.openBox(game);

        player.swapBoxes(game);
        assertNotEquals("Initial and current selection", initialSelection, player.getPrizeBox());
    }
}
