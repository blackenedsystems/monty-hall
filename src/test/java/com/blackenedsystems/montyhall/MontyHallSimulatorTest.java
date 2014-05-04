package com.blackenedsystems.montyhall;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alan Tibbetts
 * @since 3/5/14 12:51
 */
public class MontyHallSimulatorTest {

    @Test
    public void simulateWin_playerDoesNotChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        boolean doesPlayerWin = mhs.executeGame(new Game(true), false);
        assertTrue("Player Wins", doesPlayerWin);
    }

    @Test
    public void simulateWin_playerMakesChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        boolean doesPlayerWin = mhs.executeGame(new Game(false), true);
        assertTrue("Player Wins", doesPlayerWin);
    }

    @Test
    public void simulateLoss_playerDoesNotChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        boolean doesPlayerWin = mhs.executeGame(new Game(false), false);
        assertFalse("Player Wins", doesPlayerWin);
    }

    @Test
    public void simulateLoss_playerMakesChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        boolean doesPlayerWin = mhs.executeGame(new Game(true), true);
        assertFalse("Player Wins", doesPlayerWin);
    }

    @Test
    public void runSimulation_playerChanges() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        SimulationStats stats = mhs.simulate(100, true);
        assertNotNull("Stats", stats);
        assertEquals("Wins + Losses = 100", 100, stats.getWins() + stats.getLosses());
    }

    @Test
    public void runSimulation_playerDoesNotChange() {
        MontyHallSimulator mhs = new MontyHallSimulator();
        SimulationStats stats = mhs.simulate(100, false);
        assertNotNull("Stats", stats);
        assertEquals("Wins + Losses = 100", 100, stats.getWins() + stats.getLosses());
    }
}
