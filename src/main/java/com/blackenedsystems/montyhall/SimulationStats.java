package com.blackenedsystems.montyhall;

import java.math.BigDecimal;

/**
 * Statistics generated during the simulation of a number of games of the Monty Hall Problem.
 *
 * @author Alan Tibbetts
 * @since 3/5/14 20:01
 */
public class SimulationStats {

    private int wins = 0;
    private int losses = 0;
    private BigDecimal winPercentage;

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementLosses() {
        losses++;
    }

    public BigDecimal getWinPercentage() {
        if (winPercentage == null) {
            BigDecimal total = new BigDecimal(wins + losses);
            winPercentage = new BigDecimal(wins).divide(total).multiply(new BigDecimal(100));
        }

        return winPercentage;
    }

    @Override
    public String toString() {
        return "SimulationStats{" +
                "total runs = " + (wins + losses) +
                ", wins = " + wins +
                ", losses = " + losses +
                ", win% = " + getWinPercentage() +
                "}";
    }
}
