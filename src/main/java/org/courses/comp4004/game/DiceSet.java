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

    public int countDiceFaces(String diceFigure) {
        if (USE_STREAMS) {

            long count = diceSet.stream()
                    .filter(s -> s.getFigure().compareTo(diceFigure) == 0)
                    .count();

            return Long.valueOf(count).intValue();
        } else {
            int count = 0;

            for (int i = 0; i < diceSet.size(); i++) {
                count = count + (diceSet.get(i).getFigure().compareTo(diceFigure) == 0 ? 1 : 0);
            }
            return count;
        }
    }

    public String getDiceFacesCounts(String diceFace) {
        return countDiceFaces(diceFace) + " " + diceFace;
    }

    public String getDiceFacesCounts() {
        String buffer;

        buffer = countDiceFaces("skull") + " skull, "
                + countDiceFaces("parrot") + " parrot, "
                + countDiceFaces("sword") + " sword, "
                + countDiceFaces("diamond") + " diamond, "
                + countDiceFaces("coin") + " coin, "
                + countDiceFaces("monkey") + " monkey";

        return buffer;
    }

    public ArrayList<Integer> getDiceFacesCountsArrayCard(FCard fCard) {
        ArrayList<Integer> buffer = new ArrayList<Integer>();

        buffer.add(countDiceFaces("skull"));
        buffer.add(countDiceFaces("sword"));
        buffer.add(countDiceFaces("monkey"));
        buffer.add(countDiceFaces("parrot"));
        if (fCard.getFigure().equals("Diamond")) {
            buffer.add(countDiceFaces("diamond") + 1);
        } else {
            buffer.add(countDiceFaces("diamond"));
        }
        if (fCard.getFigure().equals("Coin")) {
            buffer.add(countDiceFaces("coin") + 1);
        } else {
            buffer.add(countDiceFaces("coin"));
        }

        return buffer;
    }

    public ArrayList<Integer> getDiceFacesCountsArray() {
        ArrayList<Integer> buffer = new ArrayList<Integer>();

        buffer.add(countDiceFaces("skull"));
        buffer.add(countDiceFaces("parrot"));
        buffer.add(countDiceFaces("sword"));
        buffer.add(countDiceFaces("diamond"));
        buffer.add(countDiceFaces("coin"));
        buffer.add(countDiceFaces("monkey"));

        return buffer;
    }
}
