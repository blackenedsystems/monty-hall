package com.blackenedsystems.montyhall;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alan Tibbetts
 * @since 3/5/14 12:35
 */
public class PrizeBoxTest {

    @Test
    public void boxIsWinner() {
        PrizeBox prizeBox = new PrizeBox(PrizeBox.WINNER);
        assertTrue("PrizeBox is winner", prizeBox.isWinner());
    }

    @Test
    public void boxIsLoser() {
        PrizeBox prizeBox = new PrizeBox(PrizeBox.LOSER);
        assertFalse("PrizeBox is winner", prizeBox.isWinner());
    }
}
