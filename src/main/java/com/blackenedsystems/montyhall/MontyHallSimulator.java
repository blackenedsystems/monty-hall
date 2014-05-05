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

    private SimulationStats playerSwapsBoxStats;
    private SimulationStats playerKeepsOriginalBoxStats;

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

    public void runSimulation(final int numberOfIterations) {
        playerKeepsOriginalBoxStats = simulate(numberOfIterations, false);
        System.out.println("\n\nPlayer stuck with original choice: " + playerKeepsOriginalBoxStats.toString());

        playerSwapsBoxStats = simulate(numberOfIterations, true);
        System.out.println("Player swapped boxes             : " + playerSwapsBoxStats.toString());

        if (playerSwapsBoxStats.getWinPercentage().compareTo(playerKeepsOriginalBoxStats.getWinPercentage()) > 0) {
            System.out.println("\nBest option for player: swap when given the opportunity.\n");
        } else {
            System.out.println("\nBest option for player: do not swap when given the opportunity.\n");
        }
    }

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

    public boolean executeGame(final boolean playerChangesBox) {
        return executeGame(new Game(), playerChangesBox);
    }

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

    public SimulationStats getPlayerSwapsBoxStats() {
        return playerSwapsBoxStats;
    }

    public SimulationStats getPlayerKeepsOriginalBoxStats() {
        return playerKeepsOriginalBoxStats;
    }
}