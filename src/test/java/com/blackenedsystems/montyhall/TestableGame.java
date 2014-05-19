package com.blackenedsystems.montyhall;

import static com.google.common.base.Preconditions.checkState;

/**
 * Extends the Game class with methods specific to testing.
 *
 * @author Alan Tibbetts
 * @since 19/5/14 16:55
 */
public class TestableGame extends Game {
    /**
     * @return the last remaining box, after the player has made a selection and the host has opened a losing box.
     */
    public PrizeBox getUnselectedBox() {
        checkState(prizeBoxes.size() == 1, "There should be only one remaining box.");
        return prizeBoxes.get(0);
    }

    /**
     * @return the number of prize boxes in the game
     */
    public final int numberOfBoxes() {
        return prizeBoxes.size();
    }

    /**
     * @return a count of the number of prize boxes in this game marked as winners.
     */
    public final boolean hasOneWinner() {
        int winners = 0;
        for (PrizeBox prizePrizeBox : prizeBoxes) {
            if (prizePrizeBox.isWinner()) {
                winners++;
            }
        }
        return winners == 1;
    }
}
