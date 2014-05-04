package com.blackenedsystems.montyhall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Alan Tibbetts
 * @since 3/5/14 12:39
 */
public class Game {

    private final int WINNING_BOX = 0;
    private final int DEFAULT_LOSING_BOX = 1;

    private final List<PrizeBox> prizePrizeBoxes = new ArrayList<PrizeBox>(3);

    private int playerSelection = -1;
    private int hostSelection = -1;

    public Game() {
        initialisePrizeBoxes();
    }

    public Game(final boolean playerSelectedWinner) {
        initialisePrizeBoxes();
        playerSelection = playerSelectedWinner ? WINNING_BOX : DEFAULT_LOSING_BOX;
    }

    private void initialisePrizeBoxes() {
        // For the purposes of this simulator, the winning box is always first in the list.
        prizePrizeBoxes.add(new PrizeBox(PrizeBox.WINNER));
        prizePrizeBoxes.add(new PrizeBox(PrizeBox.LOSER));
        prizePrizeBoxes.add(new PrizeBox(PrizeBox.LOSER));
    }

    /**
     * @return the number of prize boxes in the game
     */
    final int numberOfBoxes() {
        return prizePrizeBoxes.size();
    }

    /**
     * @return a count of the number of prize boxes in this game marked as winners.
     */
    final boolean hasOneWinner() {
        int winners = 0;
        for (PrizeBox prizePrizeBox : prizePrizeBoxes) {
            if (prizePrizeBox.isWinner()) {
                winners++;
            }
        }
        return winners == 1;
    }

    /**
     * Player selects one of the three boxes (at random)
     */
    public void makePlayerSelection() {
        Random random = new Random();
        playerSelection = random.nextInt(3);
    }

    boolean hasPlayerMadeSelection() {
        return playerSelection != -1;
    }

    public void makeHostSelection() {
        if (playerSelection == WINNING_BOX) {
            Random random = new Random();
            hostSelection = random.nextInt(2) + 1;
        } else {
            hostSelection = 3 - playerSelection;
        }
    }

    boolean hostSelectionDiffersFromPlayers() {
        return playerSelection != hostSelection;
    }

    /**
     * Player has decided to change his selection.
     */
    public void changePlayerSelection() {
        playerSelection = 3 - (playerSelection + hostSelection);
    }

    /**
     * @return did the player choose the winning box?
     */
    public boolean doesPlayerWin() {
        return playerSelection == 0;
    }
}
