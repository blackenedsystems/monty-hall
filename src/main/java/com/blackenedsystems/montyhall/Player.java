package com.blackenedsystems.montyhall;

import com.blackenedsystems.montyhall.PrizeBox;

import static com.google.common.base.Preconditions.checkState;

/**
 * Represents a player in the Monty Hall Problem, ie the person trying to select a winning prize box.
 *
 * @author Alan Tibbetts
 * @since 4/5/14 15:57
 */
public class Player {

    private PrizeBox prizeBox;

    public void makeSelection(final Game game) {
        checkState(this.prizeBox == null, "Player has already selected a prize box");

        this.prizeBox = game.makePlayerSelection();
    }

    public PrizeBox getPrizeBox() {
        return prizeBox;
    }

    public boolean isWinner() {
        return prizeBox == PrizeBox.WINNER;
    }

    public void swapBoxes(final Game game) {
        this.prizeBox = game.swapBoxes(prizeBox);
    }
}
