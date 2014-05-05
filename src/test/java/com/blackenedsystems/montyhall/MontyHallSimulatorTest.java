package com.blackenedsystems.montyhall;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alan Tibbetts
 * @since 3/5/14 12:51
 */
public class MontyHallSimulatorTest {

    private static final int NUMBER_OF_ITERATIONS = 1000;  // should be enough to be statistically valid

    @Test
    public void simulateWin_playerDoesNotChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        Game mockGame = mock(Game.class);

        PrizeBox winningBox = new PrizeBox(PrizeBox.WINNER);
        PrizeBox losingBox = new PrizeBox(PrizeBox.LOSER);

        when(mockGame.makePlayerSelection()).thenReturn(winningBox);
        when(mockGame.makeHostSelection()).thenReturn(losingBox);

        boolean doesPlayerWin = mhs.executeGame(mockGame, false);
        assertTrue("Player Wins", doesPlayerWin);
    }

    @Test
    public void simulateWin_playerMakesChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        Game mockGame = mock(Game.class);

        PrizeBox winningBox = new PrizeBox(PrizeBox.WINNER);
        PrizeBox losingBox = new PrizeBox(PrizeBox.LOSER);

        when(mockGame.makePlayerSelection()).thenReturn(losingBox);
        when(mockGame.makeHostSelection()).thenReturn(losingBox);
        when(mockGame.swapBoxes(losingBox)).thenReturn(winningBox);

        boolean doesPlayerWin = mhs.executeGame(mockGame, true);
        assertTrue("Player Wins", doesPlayerWin);
    }

    @Test
    public void simulateLoss_playerDoesNotChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        Game mockGame = mock(Game.class);

        PrizeBox losingBox = new PrizeBox(PrizeBox.LOSER);

        when(mockGame.makePlayerSelection()).thenReturn(losingBox);
        when(mockGame.makeHostSelection()).thenReturn(losingBox);

        boolean doesPlayerWin = mhs.executeGame(mockGame, false);
        assertFalse("Player Loses", doesPlayerWin);
    }

    @Test
    public void simulateLoss_playerMakesChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        Game mockGame = mock(Game.class);

        PrizeBox winningBox = new PrizeBox(PrizeBox.WINNER);
        PrizeBox losingBox = new PrizeBox(PrizeBox.LOSER);

        when(mockGame.makePlayerSelection()).thenReturn(winningBox);
        when(mockGame.makeHostSelection()).thenReturn(losingBox);
        when(mockGame.swapBoxes(winningBox)).thenReturn(losingBox);

        boolean doesPlayerWin = mhs.executeGame(mockGame, true);
        assertFalse("Player Loses", doesPlayerWin);
    }

    @Test
    public void runSimulation_playerChanges() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        SimulationStats stats = mhs.simulate(NUMBER_OF_ITERATIONS, true);
        assertNotNull("Stats", stats);
        assertEquals("Wins + Losses = " + NUMBER_OF_ITERATIONS, NUMBER_OF_ITERATIONS, stats.getWins() + stats.getLosses());
        assertTrue("Player wins more than 50%", stats.getWinPercentage().compareTo(new BigDecimal("50")) > 0);
    }

    @Test
    public void runSimulation_playerDoesNotChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        SimulationStats stats = mhs.simulate(NUMBER_OF_ITERATIONS, false);
        assertNotNull("Stats", stats);
        assertEquals("Wins + Losses = " + NUMBER_OF_ITERATIONS, NUMBER_OF_ITERATIONS, stats.getWins() + stats.getLosses());
        assertTrue("Player wins less than 50%", stats.getWinPercentage().compareTo(new BigDecimal("50")) < 0);
    }

    @Test
    public void runSimulation() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        mhs.runSimulation(500);
        assertNotNull("Changing Stats", mhs.getPlayerSwapsBoxStats());
        assertNotNull("Not Changing Stats", mhs.getPlayerKeepsOriginalBoxStats());
        assertTrue("Player wins more than 50% when swapping", mhs.getPlayerSwapsBoxStats().getWinPercentage().compareTo(new BigDecimal("50")) > 0);
        assertTrue("Player wins less than 50% when sticking", mhs.getPlayerKeepsOriginalBoxStats().getWinPercentage().compareTo(new BigDecimal("50")) < 0);
    }
}
