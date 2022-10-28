package org.courses.comp4004.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

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


    /**
     * This method enables all roll in the set. There might be the case that after several
     * (We think first roll on the whole set and then subsequent rerolls on the subset) rolls.
     * This method is called in roll (the very firs roll for a player) so the player will roll the whole set.
     *
     */
    public void enableAllDiceRoll(){
        diceSet.stream().
                forEach(dice -> dice.setCanRoll(true));
    }

    /**
     * This method disables dice roll in the whole set. (Read above method for more clarity)
     * This method is not to be used unless to facilitate testing.
     */
    public void disableAllDiceRoll(){
        diceSet.stream().
                forEach(dice -> dice.setCanRoll(false));

    }

    /**
     * It is used for the first roll;
     * 1. Each dice has a flag that disable (or enable rolling); this method first test if parameters are correct
     * and then enables flag for all dice. (There can be disabled dices from previous player rolls.
     * @param diceFigures
     * @return
     */
    public String roll(String diceFigures) {
        final String[] status = {"" };
        String[] figureToRoll = {""};
        String[] figureArray = diceFigures.trim().split("\\s*,\\s*");
        List<String> figureList = new ArrayList<String>();
        Collections.addAll(figureList, figureArray);

        if (USE_STREAMS) {
            Map<String, Long> figureCount = figureList.stream().collect(Collectors.groupingBy(figure -> figure, Collectors.counting()));
            Map<String, Long> figureDiceSetCount = diceSet.stream().collect(Collectors.groupingBy(dice -> dice.getFigure(), Collectors.counting()));
            figureCount.forEach((figure, count) -> {
                // Check counts
                long availableCount = isNull(figureDiceSetCount.get(figure)) ? 0 : figureDiceSetCount.get(figure);
                if (count > availableCount) {
                    // Error: Try to roll more figures than available in the dice set
                    status[0] = status[0] + "request roll " + Long.valueOf(count).intValue() + " " + figure + " available "
                            + Long.valueOf(availableCount).intValue() + ", ";
                }
            });
            // If status is empty the roll can be done otherwise return status

            if (status[0].length() == 0) {  //no errors
                //Enable roll of all dies that could be skull from the previous player
                enableAllDiceRoll();

                diceSet.stream().
                        forEach(dice -> {
                            //Returns: This method returns new remapped value associated with the specified key, or null if mapping returns null.
                            figureToRoll[0] = dice.getFigure();
                            if ( !isNull(figureCount.get(dice.getFigure())) && figureCount.get(dice.getFigure()).intValue() != 0) {
                                if (!isNull(figureCount.computeIfPresent(dice.getFigure(), (key, oldValue) -> oldValue - 1))) {
                                    if(dice.getCanRoll() && !dice.getCanHold()) {
                                        dice.roll();
                                        dice.disableRollIfFigure("skull");
                                    }
                                }
                            }
                        });
            }
        }

        int sLength = status[0].length();
        if (sLength != 0 && (status[0].substring(sLength - 2, sLength)).equals(", ")) {
            status[0] = status[0].substring(0, sLength - 2);
        }
        return status[0].length() != 0 ? status[0] = "Error: " + status[0] : "";
    }

    /**
     * It is used for subsequent rerolls;
     * 1. No skull face from the roll can be used in subsequent rolls; player has to put them aside
     * 2. No less than 2 dice must be used in the subsequent rerolls
     * @param diceFigures
     */
    public String reroll(String diceFigures){
        final boolean TRACEON    = true;
        final String[] status = {"" };
        String[] figureToRoll = {""};
        String[] figureArray = diceFigures.trim().split("\\s*,\\s*");
        List<String> figureList = new ArrayList<String>();
        Collections.addAll(figureList, figureArray);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        RuleResult ruleResult;

        Map<String, Long> figureCount = figureList.stream().collect(Collectors.groupingBy(figure -> figure, Collectors.counting()));
        Map<String, Long> figureDiceSetCount = diceSet.stream().collect(Collectors.groupingBy(dice -> dice.getFigure(), Collectors.counting()));

        // Check the preconditions for re-roll
        // 1. check number of parameters for the rules
        // 2. it is not allowable to re-roll skulls (skulls cannot be re-rolled)
        ruleResult = scoreEvaluator.rulePlayerCannotRerollLessThan2dice(diceFigures);
        status[0] = status[0] + ruleResult.getMessage();
        if(status[0].length() != 0){
            return status[0];
        }


        if (status[0].length() == 0) {  //no errors
            //Enable roll of all dies that could be skull from the previous player
            //disableAllDiceRoll();

            diceSet.stream().
                    forEach(dice -> {
                        //Returns: This method returns new remapped value associated with the specified key, or null if mapping returns null.
                        figureToRoll[0] = dice.getFigure();
                        if ( !isNull(figureCount.get(dice.getFigure())) && figureCount.get(dice.getFigure()).intValue() != 0) {
                            // A die with a figure matching figure specified in the re-roll selection has been found in the diceSet;
                            //
                            if (!isNull(figureCount.computeIfPresent(dice.getFigure(), (key, oldValue) -> oldValue - 1))) {
                                if(dice.getCanRoll() && !dice.getCanHold()) {
                                    dice.roll();
                                    dice.disableRollIfFigure("skull");
                                }
                            }
                        }else{
                            dice.setCanRoll(false);
                        }
                    });
        }
        return status[0];
    }

    public String rerollSetOutcome(String diceFigures, String setFigures) {
        final boolean TRACEON    = true;
        final String[] status = {"" };
        String[] figureToRoll = {""};
        String[] figureArray = diceFigures.trim().split("\\s*,\\s*");
        String[] figureSetArray = setFigures.trim().split("\\s*,\\s*");
        List<String> figureList = new ArrayList<String>();
        List<String> figureSetList = new ArrayList<String>();
        Collections.addAll(figureList, figureArray);
        Collections.addAll(figureSetList, figureSetArray);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        RuleResult ruleResult;

        AtomicInteger figureSetCounter = new AtomicInteger();

        Map<String, Long> figureCount = figureList.stream().collect(Collectors.groupingBy(figure -> figure, Collectors.counting()));
        Map<String, Long> figureDiceSetCount = diceSet.stream().collect(Collectors.groupingBy(dice -> dice.getFigure(), Collectors.counting()));

        // Check the preconditions for re-roll
        // 1. check number of parameters for the rules
        // 2. it is not allowable to re-roll skulls (skulls cannot be re-rolled)
        ruleResult = scoreEvaluator.rulePlayerCannotRerollLessThan2dice(diceFigures);
        status[0] = status[0] + ruleResult.getMessage();
        if(status[0].length() != 0){
            return status[0];
        }


        if (status[0].length() == 0) {  //no errors
            //Enable roll of all dies that could be skull from the previous player
            //disableAllDiceRoll();

            diceSet.stream().
                    forEach(dice -> {
                        //Returns: This method returns new remapped value associated with the specified key, or null if mapping returns null.
                        figureToRoll[0] = dice.getFigure();
                        if ( !isNull(figureCount.get(dice.getFigure())) && figureCount.get(dice.getFigure()).intValue() != 0) {
                            // A die with a figure matching figure specified in the re-roll selection has been found in the diceSet;
                            if (!isNull(figureCount.computeIfPresent(dice.getFigure(), (key, oldValue) -> oldValue - 1))) {
                                if(dice.getCanRoll() && !dice.getCanHold()) {
                                    dice.setFigure(figureSetList.get(figureSetCounter.get()));
                                    figureSetCounter.set(figureSetCounter.get() + 1);
                                }
                            }
                        }else{
                            dice.setCanRoll(false);
                        }
                    });
        }
        return status[0];
    }
}
