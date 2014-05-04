package com.blackenedsystems.montyhall;

/**
 * Represents a player in the Monty Hall Problem, ie the person trying to select a winning prize box.
 *
 * @author Alan Tibbetts
 * @since 4/5/14 15:57
 */
public class Player {

    private PrizeBox prizeBox;

    public void makeSelection(final Game game) {
        if (prizeBox != null) {
            throw new RuntimeException("Player has already selected a prize box");
        }
        this.prizeBox = game.makePlayerSelection();
    }

    public PrizeBox getPrizeBox() {
        return prizeBox;
    }

    public boolean isWinner() {
        return prizeBox.isWinner();
    }

    public void swapBoxes(final Game game) {
        this.prizeBox = game.swapBoxes(prizeBox);
    }
}
