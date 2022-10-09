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

}
