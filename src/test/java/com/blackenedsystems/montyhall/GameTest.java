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
    public void gameInitialisedOk_asWinner() {
        Game game = new Game(true);
        assertNotNull("Game", game);
        assertEquals("Number of Boxes", 3, game.numberOfBoxes());
        assertTrue("One and only one winner", game.hasOneWinner());
        assertTrue("Player Wins", game.doesPlayerWin());
    }

    @Test
    public void playerMakesSelection() {
        Game game = new Game();
        assertFalse("Player not made selection", game.hasPlayerMadeSelection());

        game.makePlayerSelection();
        assertTrue("Player made selection", game.hasPlayerMadeSelection());
    }

    @Test
    public void hostOffersAnotherBox() {
        Game game = new Game();
        game.makePlayerSelection();
        game.makeHostSelection();
        assertTrue("Host selection different from player's", game.hostSelectionDiffersFromPlayers());
    }

    @Test
    public void playerSwitchesBoxes() {
        Game game = new Game();
        game.makePlayerSelection();
        game.makeHostSelection();
        assertTrue("Host selection different from player's", game.hostSelectionDiffersFromPlayers());

        game.changePlayerSelection();
        assertTrue("Host selection different from player's", game.hostSelectionDiffersFromPlayers());
    }

    @Test
    public void playerWins_initialWinNoChange() {
        Game game = new Game(true);
        game.makeHostSelection();
        assertTrue("Player Wins", game.doesPlayerWin());
    }

    @Test
    public void playerLoses_initialWinChangesBox() {
        Game game = new Game(true);
        game.makeHostSelection();
        game.changePlayerSelection();
        assertFalse("Player Loses", game.doesPlayerWin());
    }

    @Test
    public void playerWins_initialLoseChangesBox() {
        Game game = new Game(false);
        game.makeHostSelection();
        game.changePlayerSelection();
        assertTrue("Player Wins", game.doesPlayerWin());
    }

    @Test
    public void playerLoses_initialLoseNoChange() {
        Game game = new Game(false);
        game.makeHostSelection();
        assertFalse("Player Loses", game.doesPlayerWin());
    }
}
