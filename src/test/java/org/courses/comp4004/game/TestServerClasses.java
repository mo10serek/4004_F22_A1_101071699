package org.courses.comp4004.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @DisplayName("testGetDiceFaceCountsParameter")
    void testGetDiceFaceCountsParameter() {
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("coin, coin, coin, coin, monkey, monkey, monkey, monkey");
        Assertions.assertEquals(diceSet.getDiceFacesCounts("coin"), "4 coin");
        Assertions.assertEquals(diceSet.getDiceFacesCounts("monkey"), "4 monkey");
        Assertions.assertEquals(diceSet.getDiceFacesCounts("aa"), "0 aa");
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
        Assertions.assertEquals(fCardDeck.draw("Chest").getFigure(), "Chest");
        Assertions.assertEquals(fCardDeck.draw("Sorceress").getFigure(), "Sorceress");
        Assertions.assertEquals(fCardDeck.draw("Captain").getFigure(), "Captain");
        Assertions.assertEquals(fCardDeck.draw("Monkey&Parrot").getFigure(), "Monkey&Parrot");
        Assertions.assertEquals(fCardDeck.draw("Diamond").getFigure(), "Diamond");
        Assertions.assertEquals(fCardDeck.draw("Coin").getFigure(), "Coin");
        Assertions.assertEquals(fCardDeck.draw("2skulls").getFigure(), "2skulls");
        Assertions.assertEquals(fCardDeck.draw("1skull").getFigure(), "1skull");
        Assertions.assertEquals(fCardDeck.draw("2swords").getFigure(), "2swords");
        Assertions.assertEquals(fCardDeck.draw("3swords").getFigure(), "3swords");
        Assertions.assertEquals(fCardDeck.draw("4swords").getFigure(), "4swords");
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

    //PostStatus class
    @Test
    @DisplayName("testPostStatus")
    void testPostStatus() {
        PostStatus postStatus = new PostStatus("this is a test", true);
        Assertions.assertEquals(postStatus.outMsg, "this is a test");
        Assertions.assertTrue(postStatus.success);
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
        Assertions.assertEquals(ruleResult.toString(), "true, =50, ");
    }

    //ScorePad class
    @Test
    @DisplayName("testScorePad")
    void testScorePad() {
        ScorePad scorePad = new ScorePad();
        scorePad.setTotalScore(50);
        Assertions.assertEquals(scorePad.getTotalScore(), 50);
        scorePad.addScore(25);
        Assertions.assertEquals(scorePad.getTotalScore(), 75);
    }

    //ScoreEvaluator class
    @Test
    @DisplayName("testRulePlayerCannotReRollLessThan2dice")
    void testRulePlayerCannotReRollLessThan2dice() {
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        RuleResult ruleResult = scoreEvaluator.rulePlayerCannotRerollLessThan2dice("sword");
        Assertions.assertEquals(ruleResult.toString(), "true, =0, ");
        ruleResult = scoreEvaluator.rulePlayerCannotRerollLessThan2dice("monkey, sword");
        Assertions.assertEquals(ruleResult.toString(), "false, =0, ");
    }

    @Test
    @DisplayName("testRulePlayerDieIf3Skulls")
    void testRulePlayerDieIf3Skulls() {
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("none", 0);
        diceSet.setRollOutcome("sword, skull, parrot, sword, skull, parrot, sword, monkey");
        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);
        Assertions.assertEquals(ruleResult.toString(), "false, =0, ");
        diceSet.setRollOutcome("skull, skull, skull, sword, sword, sword, coin, coin");
        ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);
        Assertions.assertEquals(ruleResult.toString(), "true, =0, ");
    }

    @Test
    @DisplayName("ruleSetOfIdenticalObjects")
    void testRuleSetOfIdenticalObjects() {
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("none", 0);
        diceSet.setRollOutcome("sword, sword, sword, skull, parrot, parrot, parrot, parrot");
        RuleResult ruleResult = scoreEvaluator.ruleSetOfIdenticalObjects(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), 300);
    }

    //LineParser class
    @Test
    @DisplayName("testLineParser")
    void testLineParserClass() {
        LineParser lineParser = new LineParser();
        lineParser.setLine("parameter");
        Assertions.assertEquals(lineParser.getLine(), "parameter");
        lineParser.setCmd("command");
        Assertions.assertEquals(lineParser.getCmd(), "command");
        lineParser.setParmsLine("parameter line");
        Assertions.assertEquals(lineParser.getParmsLine(), "parameter line");
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        lineParser.setParmsList(list);
        Assertions.assertEquals(lineParser.getParmsList().get(1),"two");
        Assertions.assertEquals(lineParser.toString(), "LineParser{" +
                "line='parameter', " +
                "cmd='command', " +
                "parmsLine='parameter line', " +
                "parmsList=[one, two, three]}");
    }

    @Test
    @DisplayName("testInit function")
    void testInt() {
        LineParser lineParser = new LineParser();
        lineParser.init("roll monkey, parrot, skull");
        Assertions.assertEquals(lineParser.getParmsList().get(0),"monkey");
        Assertions.assertEquals(lineParser.getParmsList().get(1),"parrot");
        Assertions.assertEquals(lineParser.getParmsList().get(2),"skull");
    }

    @Test
    @DisplayName("messageParameter")
    void testMessageParameter(){
        RuleResult ruleResult = new RuleResult(true, 0, "first Message");
        Assertions.assertEquals(ruleResult.getMessage(), "first Message");
        ruleResult.setMessage("second Message");
        Assertions.assertEquals(ruleResult.getMessage(), "second Message");
    }

    @Test
    @DisplayName("testPlayerStream")
    void testPlayerStream() throws IOException {
        PrintWriter out = new PrintWriter("text.txt");
        FileReader fr =new FileReader("text.txt");
        BufferedReader in = new BufferedReader(fr);

        PlayerStreams playerStreams = new PlayerStreams(out, in);

        Assertions.assertEquals(playerStreams.getIn(), in);
        Assertions.assertEquals(playerStreams.getOut(), out);
    }

    @Test
    @DisplayName("test Player Descriptor")
    void testPlayerDescriptor() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("text.txt");
        FileReader fr =new FileReader("text.txt");
        BufferedReader in = new BufferedReader(fr);

        PlayerStreams playerStreams = new PlayerStreams(out, in);
        ScorePad scorePad = new ScorePad();
        DiceSet diceSet = new DiceSet();
        FCard drawnFCard = new FCard("Coin", 0);
        PlayerDescriptor playerDescriptor = new PlayerDescriptor(playerStreams, scorePad);
        Assertions.assertEquals(playerDescriptor.toString(), "PlayerDescriptor{playerStreams=" + playerStreams +
                ", scorePad=" + scorePad + '}');
        playerDescriptor.setDiceSet(diceSet);
        playerDescriptor.setDrawnFCard(drawnFCard);
        Assertions.assertEquals(playerDescriptor.getDrawnFCard(), drawnFCard);
        Assertions.assertEquals(playerDescriptor.getDiceSet(), diceSet);
    }

    @Test
    @DisplayName("messageProcessor")
    void testMessageProcessor() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);

        PostStatus toReturn = messageProcessor.ProcessMessage(" ");
        Assertions.assertEquals(toReturn.outMsg, "unknown command");
    }

    @Test
    @DisplayName("MessageInteracting")
    void testModeInteractingCommand() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);

        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.modeInteracting);
        Assertions.assertTrue(toReturn.success);
        Assertions.assertEquals(toReturn.outMsg, Commands.modeInteracting);
    }

    @Test
    @DisplayName("MessageServerBroadcasting")
    void testModeServerBroadcasting() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);

        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.modeServerBroadcasting);
        Assertions.assertTrue(toReturn.success);
        Assertions.assertEquals(toReturn.outMsg, Commands.modeServerBroadcasting);
    }

    @Test
    @DisplayName("MessageInform")
    void testMessageInform() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);

        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.inform);
        Assertions.assertTrue(toReturn.success);
        Assertions.assertEquals(toReturn.outMsg, Commands.inform);
    }


}
