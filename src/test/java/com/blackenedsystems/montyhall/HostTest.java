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
        TestableGame game = new TestableGame();
        Player player = new Player();
        player.makeSelection(game);
        assertNotNull("Player's selection", player.getPrizeBox());

        Host host = new Host();
        host.openBox(game);
        assertNotNull("Host's opened box", host.getOpenedBox());
        assertFalse("Host opened losing box", host.getOpenedBox().isWinner());
        assertEquals("Remaining boxes", 1, game.numberOfBoxes());

        assertFalse("Player's and Host's boxes are different", player.getPrizeBox().equals(host.getOpenedBox()));
    }

    @Test(expected = IllegalStateException.class)
    public void openBox_playerNotYetSelected() {
        Game game = new Game();
        Host host = new Host();
        host.openBox(game);
    }
}
