package com.blackenedsystems.montyhall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.checkState;

/**
 * Represents a single game of the Monty Hall Problem.  Manages the list of (three) prize boxes.
 *
 * @author Alan Tibbetts
 * @since 3/5/14 12:39
 */
public class Game {

    protected List<PrizeBox> prizeBoxes;
    private Random random = new Random();

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
     * Player selects one of the three boxes (at random).
     */
    public PrizeBox makePlayerSelection() {
        checkState(prizeBoxes.size() == 3,
                "Expected 3 prizes boxes from which the player could make a selection, actually %s", prizeBoxes.size());

        int selection = random.nextInt(3);
        PrizeBox prizeBox = prizeBoxes.get(selection);
        prizeBoxes.remove(selection);
        return prizeBox;
    }


    /**
     * @return there should be two remaining boxes, one a winner, one a loser, the host will select the loser.
     */
    public PrizeBox makeHostSelection() {
        checkState(prizeBoxes.size() == 2, "Player has not yet made a selection!");
        checkState(!prizeBoxes.get(0).isWinner() || !prizeBoxes.get(1).isWinner(), "Both remaining boxes are winners!");

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
        checkState(prizeBoxes.size() == 1, "Should only be one box remaining, but there's actually " + prizeBoxes.size());

        prizeBoxes.add(prizeBox);
        PrizeBox boxToReturn = prizeBoxes.get(0);
        prizeBoxes.remove(0);
        return boxToReturn;
    }


}
