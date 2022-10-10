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

    // Row46Test	roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll 3 swords, get 2 skulls 1 sword  die
    @Test
    @DisplayName("Row46Test")
    void row46Test() {
        FCard fCard = new FCard("none", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, sword, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 300);

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, skull, skull, sword");
        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), 0);
        Assertions.assertTrue(ruleResult.isPass());
    }

    // Row47Test	roll 2 skulls, 4 parrots, 2 swords, hold parrots, reroll swords, get 1 skull 1 sword  die
    @Test
    @DisplayName("Row47Test")
    void row47Test() {
        FCard fCard = new FCard("none", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, skull, parrot, parrot, parrot, parrot, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 200);

        diceSet.setRollOutcome("skull, skull, parrot, parrot, parrot, parrot, skull, sword");
        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), 0);
        Assertions.assertTrue(ruleResult.isPass());
    }

    // Row48Test	roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll swords, get 1 skull 2 monkeys
    @Test
    @DisplayName("Row48Test")
    void row48Test() {
        FCard fCard = new FCard("none", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, sword, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 300);

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, skull, monkey, monkey");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 200);

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, skull, skull, monkey");
        System.out.println(scoreEvaluator.getScore(fCard, diceSet));
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 0);
        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), 0);
        Assertions.assertTrue(ruleResult.isPass());
    }
}
