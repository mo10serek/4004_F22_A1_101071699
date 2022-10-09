package org.courses.comp4004.game;

import java.util.ArrayList;
import java.util.List;

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
}
