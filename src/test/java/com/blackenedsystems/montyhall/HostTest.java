package com.blackenedsystems.montyhall;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alan Tibbetts
 * @since 4/5/14 16:07
 */
public class HostTest {

    @Test
    public void openBox_ok() {
        Game game = new Game();
        Player player = new Player();
        player.makeSelection(game);
        assertNotNull("Player's selection", player.getPrizeBox());

        Host host = new Host();
        host.openBox(game);
        assertNotNull("Host's opened box", host.getOpenedBox());
        assertTrue("Host opened losing box", host.getOpenedBox() == PrizeBox.LOSER);
        assertEquals("Remaining boxes", 1, game.numberOfBoxes());
    }

    @Test(expected = IllegalStateException.class)
    public void openBox_playerNotYetSelected() {
        Game game = new Game();
        Host host = new Host();
        host.openBox(game);
    }
}
