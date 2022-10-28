package org.courses.comp4004.game;

import java.util.ArrayList;
import java.util.List;

public class ScoreEvaluator {

    int getScore(FCard fCard, DiceSet diceSet) {
        int score = 0;
        ScorePad scorePad = new ScorePad();

        if (rulePlayerDieIf3Skulls(diceSet, fCard).isPass()) {
            return 0;
        }

        RuleResult ruleResultSetsOfIdenticalObjects  = ruleSetOfIdenticalObjects(diceSet, fCard);
        scorePad.addScore(ruleResultSetsOfIdenticalObjects.getScore());

        RuleResult ruleDiamondsAndGold = ruleDiamondsAndGold(diceSet, fCard);
        scorePad.addScore(ruleDiamondsAndGold.getScore());

        RuleResult ruleFullChest = ruleFullChest(diceSet);
        scorePad.addScore(ruleFullChest.getScore());

        score = scorePad.getTotalScore();
        if (fCard.getFigure().contains("Captain")) {
            score = score * 2;
        }
        return score;
    }

    public RuleResult ruleRollOneSkull(DiceSet diceSet, FCard fCard) {
        if (fCard.getFigure().contains("Sorceress")) {
            return new RuleResult(true, 0, "player use the sorceress card to roll one skull");
        }
        return new RuleResult(false, 0, "player tried to roll one skull but does not have the " +
                "sorceress card");
    }

    public RuleResult ruleGoToSkullIslandIf4Skulls(DiceSet diceSet, FCard fCard) {
        int currentNumberOfSkulls = 0;
        if (fCard.getFigure().contains("2skulls")) {
            currentNumberOfSkulls += 2;
        } else if (fCard.getFigure().contains("1skull")) {
            currentNumberOfSkulls += 1;
        }
        currentNumberOfSkulls += diceSet.countDiceFaces("skull");

        return currentNumberOfSkulls >= 4 ? new RuleResult(true, 0, "player got 4 skulls in first roll " +
                "and need to go to skull Island") : new RuleResult(false, 0);
    }

    /**
     * Rule checks if player selected less than 2 dice to roll
     * @param diceFigures
     * @return
     */
    public RuleResult rulePlayerCannotRerollLessThan2dice(String diceFigures){

        String[] figureArray = diceFigures.trim().split("\\s*,\\s*");
        return figureArray.length < 2 ? new RuleResult(true, 0) : new RuleResult(false, 0);
    }

    /**
     * Rule checks if 3 skulls are present in the dice set.
     * Returns true if rule finds that this is the case
     * @param diceSet
     * @return
     */
    public RuleResult rulePlayerDieIf3Skulls(DiceSet diceSet, FCard fCard){
        int numberOfSkulls = 0;

        numberOfSkulls += diceSet.countDiceFaces("skull");

        return numberOfSkulls == 3 ? new RuleResult(true, 0) : new RuleResult(false, 0);
    }

    public ArrayList<Integer> getDiceFacesCountsArrayCard(DiceSet diceSet, FCard fCard) {
        ArrayList<Integer> buffer = new ArrayList<Integer>();

        buffer.add(diceSet.countDiceFaces("skull"));
        buffer.add(diceSet.countDiceFaces("sword"));
        if (fCard.getFigure().equals("Monkey&Parrot")) {
            buffer.add(diceSet.countDiceFaces("monkey") +
                    diceSet.countDiceFaces("parrot"));
        } else {
            buffer.add(diceSet.countDiceFaces("monkey"));
            buffer.add(diceSet.countDiceFaces("parrot"));
        }
        if (fCard.getFigure().equals("Diamond")) {
            buffer.add(diceSet.countDiceFaces("diamond") + 1);
        } else {
            buffer.add(diceSet.countDiceFaces("diamond"));
        }
        if (fCard.getFigure().equals("Coin")) {
            buffer.add(diceSet.countDiceFaces("coin") + 1);
        } else {
            buffer.add(diceSet.countDiceFaces("coin"));
        }

        return buffer;
    }

    int getScoreWhenDiceHold(FCard fCard, DiceSet diceSet) {
        int score = 0;
        ScorePad scorePad = new ScorePad();

        DiceSet diceSetHold = new DiceSet();
        List<Dice> holdDiceList = new ArrayList<>();
        for (Dice dice : diceSet.getDiceSet()) {
            if (dice.getCanHold()) {
                holdDiceList.add(dice);
            }
        }

        diceSetHold.setDiceSet(holdDiceList);

        RuleResult ruleResultSetsOfIdenticalObjects = ruleSetOfIdenticalObjects(diceSetHold, fCard);
        scorePad.addScore(ruleResultSetsOfIdenticalObjects.getScore());

        RuleResult ruleDiamondsAndGold = ruleDiamondsAndGold(diceSetHold, fCard);
        scorePad.addScore(ruleDiamondsAndGold.getScore());

        RuleResult ruleFullChest = ruleFullChest(diceSetHold);
        scorePad.addScore(ruleFullChest.getScore());

        score = scorePad.getTotalScore();
        return score;
    }

    public RuleResult ruleCanHoldDices(DiceSet diceSet, FCard fCard) {
        if (fCard.getFigure().contains("Chest")) {
            return new RuleResult(true, 0,"dices can be hold");
        } else {
            return new RuleResult(false,0,"dices cannot be hold");
        }
    }

    public RuleResult ruleSetOfIdenticalObjects(DiceSet diceSet, FCard fCard) {
        ScorePad scorePad = new ScorePad();

        ArrayList<Integer> buffer = getDiceFacesCountsArrayCard(diceSet, fCard);

        for (int value : buffer) {
            if (value == 3) {
                scorePad.addScore(100);
            } else if (value == 4) {
                scorePad.addScore(200);
            } else if (value == 5) {
                scorePad.addScore(500);
            } else if (value == 6) {
                scorePad.addScore(1000);
            } else if (value == 7) {
                scorePad.addScore(2000);
            } else if (value >= 8) {
                scorePad.addScore(4000);
            }
        }

        return new RuleResult(true, scorePad.getTotalScore());
    }

    public RuleResult ruleDiamondsAndGold(DiceSet diceSet, FCard fCard) {
        ScorePad scorePad = new ScorePad();

        if (fCard.getFigure().contains("Coin")) {
            scorePad.addScore(100);
        } else if (fCard.getFigure().contains("Diamond")) {
            scorePad.addScore(100);
        }
        scorePad.addScore(diceSet.countDiceFaces("coin") * 100);
        scorePad.addScore(diceSet.countDiceFaces("diamond") * 100);

        return new RuleResult(true, scorePad.getTotalScore());
    }

    public RuleResult ruleFullChest(DiceSet diceSet) {
        ArrayList<Integer> buffer = diceSet.getDiceFacesCountsArray();
        int totalOfScoredDice = 0;

        for (int value: buffer) {
            if (2 < value) {
                totalOfScoredDice += value;
            }
        }

        if (totalOfScoredDice >= 8){
            return new RuleResult(true, 500);
        }
        return new RuleResult(true, 0);
    }
}
