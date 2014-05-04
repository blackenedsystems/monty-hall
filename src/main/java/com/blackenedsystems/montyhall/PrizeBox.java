package com.blackenedsystems.montyhall;

/**
 * Represents a prize box, which may or may not be a winning box.
 *
 * @author Alan Tibbetts
 * @since 3/5/14 12:34
 */
public class PrizeBox {
    public static final boolean WINNER = true;
    public static final boolean LOSER = false;

    private final boolean winner;

    public PrizeBox(boolean winner) {
        this.winner = winner;
    }

    public boolean isWinner() {
        return winner;
    }
}
