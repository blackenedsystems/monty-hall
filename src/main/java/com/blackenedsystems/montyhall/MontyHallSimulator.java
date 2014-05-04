package com.blackenedsystems.montyhall;

/**
 * Simulation of the Monty Hall Problem ...
 * <p/>
 * Problem description:
 * Assume that you are attending a TV show where you can win money by picking the right box. The game show host shows you
 * three boxes explaining that the money is in one of the boxes. He asks you to pick one of them without opening it. After you
 * have picked one of the boxes he opens one of the other two boxes which is empty. Now he turns to you and asks, do you want to
 * change your mind, picking the remaining box?
 * <p/>
 * Your task:
 * Write a program in Java randomly simulating this event over and over again in the quest of answering following question. Do I
 * stand a better chance to win if I change my mind?
 */
public class MontyHallSimulator {

    public static void main(String[] args) {
        MontyHallSimulator mhs = new MontyHallSimulator();

        SimulationStats statsForChanging = mhs.simulate(10000, true);
        System.out.println("Player made change   : " + statsForChanging.toString());

        SimulationStats statsForNotChanging = mhs.simulate(10000, false);
        System.out.println("Player did not change: " + statsForNotChanging.toString());

        if (statsForChanging.getWinPercentage().compareTo(statsForNotChanging.getWinPercentage()) > 0) {
            System.out.println("\nPlayers are best to change their initial selection.");
        } else {
            System.out.println("\nPlayers are best to stick with their initial selection.");
        }
    }

    /**
     * Run a simulation of a number of games where the player either does or does not change his selection after the host
     * reveals an empty box.
     *
     * @param numberOfGames number of games to be executed
     * @param playerChangesBox should the player change his selection after the host opens an empty box?
     * @return statistics for the simulation
     */
    public SimulationStats simulate(final int numberOfGames, final boolean playerChangesBox) {
        SimulationStats stats = new SimulationStats();
        for (int i = 0; i < numberOfGames; i++) {
            if (executeGame(playerChangesBox)) {
                stats.incrementWins();
            } else {
                stats.incrementLosses();
            }
        }
        return stats;
    }

    /**
     * @param playerChangesBox should the player change his selection after the host opens an empty box?
     * @return whether or not the player wins.
     */
    public boolean executeGame(final boolean playerChangesBox) {
        return executeGame(new Game(), playerChangesBox);
    }

    /**
     * @param game             the game to be executed (may include a player selection).
     * @param playerChangesBox should the player change his selection after the host opens an empty box?
     * @return whether or not the player wins.
     */
    boolean executeGame(final Game game, final boolean playerChangesBox) {
        if (!game.hasPlayerMadeSelection()) {
            game.makePlayerSelection();
        }

        game.makeHostSelection();

        if (playerChangesBox) {
            game.changePlayerSelection();
        }

        return game.doesPlayerWin();
    }
}
