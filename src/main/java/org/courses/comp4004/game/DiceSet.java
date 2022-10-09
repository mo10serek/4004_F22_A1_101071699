package org.courses.comp4004.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DiceSet {
    final int MAX_SIZE = 8;
    final boolean USE_STREAMS = true;
    private List<Dice> diceSet = new ArrayList<>();

    /**
     * Initialize a set of dice.
     */
    public DiceSet() {
        for (int i = 0; i < MAX_SIZE; i++) {
            diceSet.add(new Dice());
        }
    }

    @Override
    public String toString() {
        return "" + diceSet;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public List<Dice> getDiceSet() {
        return diceSet;
    }

    public void setDiceSet(List<Dice> diceSet) {
        this.diceSet = diceSet;
    }

    public void roll() {
        if (USE_STREAMS) {

            diceSet.stream().
                    forEach(dice -> dice.roll());
        } else {
            for (int i = 0; i < diceSet.size(); i++) {
                diceSet.get(i).roll();
            }
        }
    }

    public void setRollOutcome(String diceFigures) {
        String[] faceArray = diceFigures.trim().split("\\s*,\\s*");
        List<String> faceList = new ArrayList<String>();
        Collections.addAll(faceList, faceArray);

        if (USE_STREAMS) {
            int length = faceList.size();
            IntStream.range(0, length)
                    .forEach(i -> diceSet.get(i).setFigure(faceList.get(i)));
        } else {


            for (int i = 0; i < faceList.size(); i++) {
                diceSet.get(i).setFigure(faceList.get(i));
            }
        }
    }
}
