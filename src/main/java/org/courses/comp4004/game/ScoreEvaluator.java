package org.courses.comp4004.game;

import java.util.ArrayList;

public class ScoreEvaluator {

    int getScore(FCard fCard, DiceSet diceSet) {
        int score = 0;
        ScorePad scorePad = new ScorePad();

        if (rulePlayerDieIf3Skulls(diceSet, fCard).isPass()) {
            return 0;
        }

        RuleResult ruleResultSetsOfIdenticalObjects  = ruleSetOfIdenticalObjects(diceSet, fCard);
        scorePad.addScore(ruleResultSetsOfIdenticalObjects.getScore());

        score = scorePad.getTotalScore();
        return score;
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

    public RuleResult ruleSetOfIdenticalObjects(DiceSet diceSet, FCard fCard) {
        ScorePad scorePad = new ScorePad();

        ArrayList<Integer> buffer = diceSet.getDiceFacesCountsArray();

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
}
