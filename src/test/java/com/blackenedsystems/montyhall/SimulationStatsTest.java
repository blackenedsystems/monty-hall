package com.blackenedsystems.montyhall;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Alan Tibbetts
 * @since 19/5/14 16:47
 */
public class SimulationStatsTest {

    @Test
    public void getWinPercentage_ok() {
        SimulationStats simulationStats = new SimulationStats();
        simulationStats.incrementWins();
        simulationStats.incrementLosses();

        assertEquals("Wins", 1, simulationStats.getWins());
        assertEquals("Loses", 1, simulationStats.getLosses());
        assertEquals("%", new BigDecimal("50.00"), simulationStats.getWinPercentage());
    }

    @Test
    public void getWinPercentage_afterChangesStillOk() {
        SimulationStats simulationStats = new SimulationStats();
        simulationStats.incrementWins();
        simulationStats.incrementLosses();

        assertEquals("%", new BigDecimal("50.00"), simulationStats.getWinPercentage());

        simulationStats.incrementWins();
        simulationStats.incrementWins();

        assertEquals("%", new BigDecimal("75.00"), simulationStats.getWinPercentage());
    }
}
