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

    private static final int DEFAULT_NUMBER_OF_ITERATIONS = 10000;

    private SimulationStats statsForChanging;
    private SimulationStats statsForNotChanging;

    public static void main(String[] args) {
        int numberOfIterations = DEFAULT_NUMBER_OF_ITERATIONS;
        if (args.length > 0) {
            try {
                numberOfIterations = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                System.out.println("First argument must be an integer.");
            }
        }

        MontyHallSimulator mhs = new MontyHallSimulator();
        mhs.runSimulation(numberOfIterations);
    }

    /**
     * Run a simulation of the Monty Hall Problem, to compare the two use cases, when the player swaps
     * boxes after the host reveals an empty box, and when he keeps his original selection.
     *
     * @param numberOfIterations number of times to run each use case.
     */
    public void runSimulation(final int numberOfIterations) {
        statsForNotChanging = simulate(numberOfIterations, false);
        System.out.println("\n\nPlayer stuck with original choice: " + statsForNotChanging.toString());

        statsForChanging = simulate(numberOfIterations, true);
        System.out.println("Player swapped boxes             : " + statsForChanging.toString());

        if (statsForChanging.getWinPercentage().compareTo(statsForNotChanging.getWinPercentage()) > 0) {
            System.out.println("\nBest option for player: swap when given the opportunity.\n");
        } else {
            System.out.println("\nBest option for player: do not swap when given the opportunity.\n");
        }
    }

    /**
     * Run a simulation of a number of games where the player either does or does not change his selection after the host
     * reveals an empty box.
     *
     * @param numberOfGames    number of games to be executed
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
        Player player = new Player();
        player.makeSelection(game);

        Host host = new Host();
        host.openBox(game);

        if (playerChangesBox) {
            player.swapBoxes(game);
        }

        return player.isWinner();
    }

    public SimulationStats getStatsForChanging() {
        return statsForChanging;
    }

    public SimulationStats getStatsForNotChanging() {
        return statsForNotChanging;
    }
}