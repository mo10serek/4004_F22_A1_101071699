package org.courses.comp4004.game;

public class ScoreEvaluator {

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
}
