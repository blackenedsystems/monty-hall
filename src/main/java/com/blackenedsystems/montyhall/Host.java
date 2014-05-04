package com.blackenedsystems.montyhall;

/**
 * Represents the host of the gameshow in the Monty Hall problem.  The host will open the remaining losing prize box
 * after the player has made his selection.
 *
 * @author Alan Tibbetts
 * @since 4/5/14 15:57
 */
public class Host {
    private PrizeBox openedBox;

    public PrizeBox getOpenedBox() {
        return openedBox;
    }

    public void openBox(final Game game) {
        this.openedBox = game.makeHostSelection();
    }
}
