package com.blackenedsystems.montyhall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a single game of the Monty Hall Problem.  Manages the list of (three) prize boxes.
 *
 * @author Alan Tibbetts
 * @since 3/5/14 12:39
 */
public class Game {

    private List<PrizeBox> prizeBoxes;

    public Game() {
        initialisePrizeBoxes();
    }

    private void initialisePrizeBoxes() {
        prizeBoxes = new ArrayList<>(3);
        prizeBoxes.add(new PrizeBox(PrizeBox.WINNER));
        prizeBoxes.add(new PrizeBox(PrizeBox.LOSER));
        prizeBoxes.add(new PrizeBox(PrizeBox.LOSER));
    }

    /**
     * @return the number of prize boxes in the game
     */
    final int numberOfBoxes() {
        return prizeBoxes.size();
    }

    /**
     * @return a count of the number of prize boxes in this game marked as winners.
     */
    final boolean hasOneWinner() {
        int winners = 0;
        for (PrizeBox prizePrizeBox : prizeBoxes) {
            if (prizePrizeBox.isWinner()) {
                winners++;
            }
        }
        return winners == 1;
    }

    /**
     * Player selects one of the three boxes (at random).
     */
    public PrizeBox makePlayerSelection() {
        if (prizeBoxes.size() != 3) {
            throw new IllegalStateException("Expected 3 prizes boxes from which the player could make a selection, actually " + prizeBoxes.size());
        }

        Random random = new Random();
        int selection = random.nextInt(3);
        PrizeBox prizeBox = prizeBoxes.get(selection);
        prizeBoxes.remove(selection);
        return prizeBox;
    }


    /**
     * @return there should be two remaining boxes, one a winner, one a loser, the host will select the loser.
     */
    public PrizeBox makeHostSelection() {
        if (prizeBoxes.size() != 2) {
            throw new IllegalStateException("Player has not yet made a selection!");
        }

        if (prizeBoxes.get(0).isWinner() && prizeBoxes.get(1).isWinner()) {
            throw new IllegalStateException("Both remaining boxes are winners!");
        }

        int selection = (prizeBoxes.get(0).isWinner()) ? 1 : 0;

        PrizeBox prizeBox = prizeBoxes.get(selection);
        prizeBoxes.remove(selection);
        return prizeBox;
    }

    /**
     * @param prizeBox player's initial selection, this will be returned to the list of boxes.
     * @return the box that remained after the player's initial selection and the host opened a losing box.
     */
    public PrizeBox swapBoxes(final PrizeBox prizeBox) {
        if (prizeBoxes.size() > 1) {
            throw new IllegalStateException("Should only be one box remaining, but there's actually " + prizeBoxes.size());
        }

        prizeBoxes.add(prizeBox);
        PrizeBox boxToReturn = prizeBoxes.get(0);
        prizeBoxes.remove(0);
        return boxToReturn;
    }

    /**
     * @return the last remaining box, after the player has made a selection and the host has opened a losing box.
     */
    PrizeBox getLastBox() {
        if (prizeBoxes.size() > 1) {
            throw new IllegalStateException("There should be only one remaining box.");
        }
        return prizeBoxes.get(0);
    }
}
