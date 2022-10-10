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
}
