package org.courses.comp4004.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for Part1Test.
 * 44 | PART 1: getting first50 marks    (SINGLE PLAYER SCORING)
 */
public class Part1Test {

    // Row45Test	die with 3 skulls on first roll
    @Test
    @DisplayName("Row45Test")
    void row45Test() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, skull, skull, parrot, parrot, sword, sword, sword");
        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);

        Assertions.assertTrue(ruleResult.isPass());
        Assertions.assertEquals(ruleResult.getScore(), 0);
    }
}
