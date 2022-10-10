package org.courses.comp4004.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestServerClasses {

    //Dice class
    //SetDice set a dice that have a face
    @Test
    @DisplayName("SetDice")
    void setDice() {
        Dice dice = new Dice("sword");
        Assertions.assertEquals(dice.getFigure(), "sword");
        dice.roll();
        dice.setFigure("diamond");
        Assertions.assertEquals(dice.getFigure(), "diamond");
    }

    //SetDice enable and disable a dice
    @Test
    @DisplayName("DisableAndEnableDice")
    void disableAndEnableDice() {
        Dice dice = new Dice("parrot");
        Assertions.assertTrue(dice.getCanRoll());
        dice.disableRollIfFigure("parrot");
        Assertions.assertFalse(dice.getCanRoll());
        dice.setCanRoll(true);
        Assertions.assertTrue(dice.getCanRoll());
    }

    //Roll randomly dice until a figure is any other than that specified in parameter
    @Test
    @DisplayName("testRollNotFigure")
    public void testRollNotFigure() {
        Dice dice = new Dice("parrot");
        dice.rollNotFigure("coin");
        Assertions.assertFalse(dice.getFigure().contains("coin"));
    }

    //DiceSet Class
    @Test
    @DisplayName("setupSetOfDice")
    void setupSetOfDice() {
        DiceSet diceSet = new DiceSet();
        Assertions.assertEquals(diceSet.getDiceSet().size(), diceSet.getMAX_SIZE());
    }

    @Test
    @DisplayName("testSetDiceSet")
    void testSetDiceSet() {
        DiceSet diceSet = new DiceSet();
        List<Dice> dices = new ArrayList<Dice>();
        dices.add(new Dice("coin"));
        dices.add(new Dice("diamond"));
        dices.add(new Dice("sword"));
        diceSet.setDiceSet(dices);
        Assertions.assertEquals(diceSet.getDiceSet().get(0).getFigure(), "coin");
        Assertions.assertEquals(diceSet.getDiceSet().get(1).getFigure(), "diamond");
        Assertions.assertEquals(diceSet.getDiceSet().get(2).getFigure(), "sword");
    }

    @Test
    @DisplayName("testSetRollOutcome")
    void testSetRollOutcome() {
        DiceSet diceSet = new DiceSet();
        diceSet.roll();
        diceSet.setRollOutcome("skull, parrot, sword, diamond, coin, monkey, parrot, skull");
        Assertions.assertEquals(diceSet.getDiceSet().get(0).getFigure(), "skull");
        Assertions.assertEquals(diceSet.getDiceSet().get(1).getFigure(), "parrot");
        Assertions.assertEquals(diceSet.getDiceSet().get(2).getFigure(), "sword");
        Assertions.assertEquals(diceSet.getDiceSet().get(3).getFigure(), "diamond");
        Assertions.assertEquals(diceSet.getDiceSet().get(4).getFigure(), "coin");
        Assertions.assertEquals(diceSet.getDiceSet().get(5).getFigure(), "monkey");
        Assertions.assertEquals(diceSet.getDiceSet().get(6).getFigure(), "parrot");
        Assertions.assertEquals(diceSet.getDiceSet().get(7).getFigure(), "skull");
    }

    @Test
    @DisplayName("testCountDiceFaces")
    void testCountDiceFaces() {
        DiceSet diceSet =new DiceSet();
        diceSet.setRollOutcome("parrot, parrot, parrot, diamond, diamond, monkey, monkey, monkey");
        Assertions.assertEquals(diceSet.countDiceFaces("parrot"),3);
        Assertions.assertEquals(diceSet.countDiceFaces("diamond"),2);
        Assertions.assertEquals(diceSet.countDiceFaces("monkey"),3);
    }

    @Test
    @DisplayName("testGetDiceFaceCounts")
    void testGetDiceFaceCounts() {
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("coin, coin, diamond, monkey, diamond, monkey, skull, coin");
        Assertions.assertEquals(diceSet.getDiceFacesCounts("coin"), "3 coin");
        Assertions.assertEquals(diceSet.getDiceFacesCounts("diamond"), "2 diamond");
        Assertions.assertEquals(diceSet.getDiceFacesCounts("monkey"), "2 monkey");
        Assertions.assertEquals(diceSet.getDiceFacesCounts("skull"), "1 skull");
        Assertions.assertEquals(diceSet.getDiceFacesCounts(), "1 skull, 0 parrot, 0 sword, 2 diamond, 3 coin, 2 monkey");
    }

    //fCard class
    @Test
    @DisplayName("testSetValuesInFCard")
    void testSetValuesInFCard() {
        FCard fCard = new FCard("Coin", 8);
        Assertions.assertEquals(fCard.getFigure(), "Coin");
        Assertions.assertEquals(fCard.getValue(), 8);
        fCard.setFigure("Diamond");
        fCard.setValue(3);
        Assertions.assertEquals(fCard.getFigure(), "Diamond");
        Assertions.assertEquals(fCard.getValue(), 3);
    }

    //fCardDeck class
    @Test
    @DisplayName("testGetDeck")
    void testGetDeck() {
        FCardDeck fCardDeck = new FCardDeck();
        Assertions.assertEquals(fCardDeck.getDeck().get(0).getFigure(), "Chest");
        Assertions.assertEquals(fCardDeck.getDeck().get(5).getFigure(), "Sorceress");
        Assertions.assertEquals(fCardDeck.getDeck().get(9).getFigure(), "Captain");
        Assertions.assertEquals(fCardDeck.getDeck().get(13).getFigure(), "Monkey&Parrot");
        Assertions.assertEquals(fCardDeck.getDeck().get(17).getFigure(), "Diamond");
        Assertions.assertEquals(fCardDeck.getDeck().get(21).getFigure(), "Coin");
        Assertions.assertEquals(fCardDeck.getDeck().get(24).getFigure(), "2skulls");
        Assertions.assertEquals(fCardDeck.getDeck().get(27).getFigure(), "1skull");
        Assertions.assertEquals(fCardDeck.getDeck().get(30).getFigure(), "2swords");
        Assertions.assertEquals(fCardDeck.getDeck().get(32).getFigure(), "3swords");
        Assertions.assertEquals(fCardDeck.getDeck().get(34).getFigure(), "4swords");
    }

    @Test
    @DisplayName("testDraw")
    void testDraw() {
        FCardDeck fCardDeck = new FCardDeck();
        FCard fCard = fCardDeck.draw();
        Assertions.assertEquals(fCard.getFigure(), "Chest");
        Assertions.assertEquals(fCard.getValue(), 0);
        fCardDeck.shuffle();
        fCard = fCardDeck.draw("Chest");
        Assertions.assertEquals(fCard.getFigure(), "Chest");
    }

    //RuleResult class
    @Test
    @DisplayName("SetRuleResult")
    void SetRuleResult() {
        RuleResult ruleResult = new RuleResult(false, 0);
        Assertions.assertFalse(ruleResult.isPass());
        Assertions.assertEquals(ruleResult.getScore(), 0);
        ruleResult.setPass(true);
        ruleResult.setScore(50);
        Assertions.assertTrue(ruleResult.isPass());
        Assertions.assertEquals(ruleResult.getScore(), 50);
        Assertions.assertEquals(ruleResult.toString(), "true  50");
    }

}
