package org.courses.comp4004.game;

import com.beust.ah.A;
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
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("parrot, parrot, parrot, diamond, diamond, monkey, monkey, monkey");
        Assertions.assertEquals(diceSet.countDiceFaces("parrot"), 3);
        Assertions.assertEquals(diceSet.countDiceFaces("diamond"), 2);
        Assertions.assertEquals(diceSet.countDiceFaces("monkey"), 3);
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
        Assertions.assertEquals(lineParser.getParmsList().get(1), "two");
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
        Assertions.assertEquals(lineParser.getParmsList().get(0), "monkey");
        Assertions.assertEquals(lineParser.getParmsList().get(1), "parrot");
        Assertions.assertEquals(lineParser.getParmsList().get(2), "skull");
    }

    @Test
    @DisplayName("messageParameter")
    void testMessageParameter() {
        RuleResult ruleResult = new RuleResult(true, 0, "first Message");
        Assertions.assertEquals(ruleResult.getMessage(), "first Message");
        ruleResult.setMessage("second Message");
        Assertions.assertEquals(ruleResult.getMessage(), "second Message");
    }

    @Test
    @DisplayName("testPlayerStream")
    void testPlayerStream() throws IOException {
        PrintWriter out = new PrintWriter("text.txt");
        FileReader fr = new FileReader("text.txt");
        BufferedReader in = new BufferedReader(fr);

        PlayerStreams playerStreams = new PlayerStreams(out, in);

        Assertions.assertEquals(playerStreams.getIn(), in);
        Assertions.assertEquals(playerStreams.getOut(), out);
    }

    @Test
    @DisplayName("test Player Descriptor")
    void testPlayerDescriptor() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("text.txt");
        FileReader fr = new FileReader("text.txt");
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

    @Test
    @DisplayName("testRIGID")
    void testRIGID() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);

        Assertions.assertFalse(messageProcessor.getRIGID());
        messageProcessor.turnOnRIGID();
        Assertions.assertTrue(messageProcessor.getRIGID());
    }

    @Test
    @DisplayName("testInteractingPlayerDescriptor")
    void testInteractingPlayerDescriptor() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);

        messageProcessor.setInteractingPlayerDescriptor(interactingPlayerDescriptor);
        Assertions.assertEquals(messageProcessor.getInteractingPlayerDescriptor(), interactingPlayerDescriptor);

    }

    @Test
    @DisplayName("testDiceCanHoldOrNot")
    void testDiceCanHoldOrNot() {
        Dice dice = new Dice();

        Assertions.assertFalse(dice.getCanHold());
        dice.holddice();
        Assertions.assertTrue(dice.getCanHold());
        dice.setCanHold(false);
        Assertions.assertFalse(dice.getCanHold());
    }

    @Test
    @DisplayName("testEnableAndDisableAllDiceRolls")
    void testEnableAndDisableAllDiceRolls() {
        DiceSet diceSet = new DiceSet();

        for (int i = 0; i < diceSet.getMAX_SIZE(); i++)
            Assertions.assertTrue(diceSet.getDiceSet().get(i).getCanRoll());
        diceSet.disableAllDiceRoll();
        for (int i = 0; i < diceSet.getMAX_SIZE(); i++)
            Assertions.assertFalse(diceSet.getDiceSet().get(i).getCanRoll());
        diceSet.enableAllDiceRoll();
        for (int i = 0; i < diceSet.getMAX_SIZE(); i++)
            Assertions.assertTrue(diceSet.getDiceSet().get(i).getCanRoll());
    }

    @Test
    @DisplayName("testRollsInSet")
    void testRollsInSet() {
        DiceSet diceSet = new DiceSet();

        diceSet.setRollOutcome("skull, monkey, parrot, monkey, sword, sword, skull, coin");

        String message = diceSet.roll("parrot, monkey");
        System.out.println(message);
        Assertions.assertEquals(message, "");

    }

    @Test
    @DisplayName("testRerollsInSet")
    void testRerollsInSet() {
        DiceSet diceSet = new DiceSet();

        diceSet.setRollOutcome("skull, monkey, parrot, monkey, sword, sword, skull, coin");

        String message = diceSet.reroll("parrot, monkey");
        System.out.println(message);
        Assertions.assertEquals(message, "");

    }
    @Test
    @DisplayName("messageDraw")
    void testMessageDraw() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(interactingPlayerDescriptor);

        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.draw);
        Assertions.assertTrue(toReturn.success);
    }

    @Test
    @DisplayName("messageRoll")
    void testMessageRoll() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(interactingPlayerDescriptor);

        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.roll);
        Assertions.assertEquals(toReturn.outMsg, "roll, the player haven't drawn a card. Please draw a card by writing the command \'draw\'");
        Assertions.assertTrue(toReturn.success);
        toReturn = messageProcessor.ProcessMessage(Commands.draw);
        Assertions.assertTrue(toReturn.success);

    }

    @Test
    @DisplayName("messageHelp")
    void testMessageHelp() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);

        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.help);
        Assertions.assertEquals(toReturn.outMsg, Commands.outcome + " list of command to use " +
                " , outcome: notify the player of what is the current card, dice set and all of the players scores" +
                " , draw: allow the player to pick the card from the pile" +
                " , roll: allow the player to roll a set of dice or pick which dice to roll from" +
                " , <Dice1>,<Dice2>,...,<DiceN>   (player request rolling SELECTED SUBSET OF DICE)" +
                " , done: the player end their turn" +
                " , use.sorceress: to allow the player have the sorceress card");
        Assertions.assertTrue(toReturn.success);
    }

    @Test
    @DisplayName("testScoreNormal")
    void testScoreNormal() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("coin, diamond, coin, skull, skull, monkey, sword, sword");
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor playerDescriptor = new PlayerDescriptor();
        ScorePad scorePad = new ScorePad();
        playerDescriptor.setScorePad(scorePad);
        playerDescriptor.setDrawnFCard(new FCard("Coin", 0));
        playerDescriptorList.add(playerDescriptor);

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptor);

        PostStatus toReturn = messageProcessor.scoreNormal();

        Assertions.assertEquals(toReturn.outMsg, "outcome Coin, [coin, diamond, coin, skull, skull, monkey, " +
                "sword, sword], 500, the player got a score of 500 points from this dice set");
        Assertions.assertTrue(toReturn.success);
    }

    @Test
    @DisplayName("testUseTheSorceressCard")
    public void testUseTheSorceressCard() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("coin, diamond, coin, skull, skull, monkey, sword, sword");
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor playerDescriptor = new PlayerDescriptor();
        ScorePad scorePad = new ScorePad();
        playerDescriptor.setScorePad(scorePad);
        playerDescriptor.setDrawnFCard(new FCard("Coin", 0));
        playerDescriptorList.add(playerDescriptor);

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptor);
        boolean itWorked = messageProcessor.useTheSorceressCard();
        Assertions.assertTrue(itWorked);
        diceSet.setRollOutcome("coin, diamond, coin, sword, sword, monkey, sword, sword");
        messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptor);
        itWorked = messageProcessor.useTheSorceressCard();
        Assertions.assertFalse(itWorked);
    }

    @Test
    @DisplayName("testRerollSetOutcome")
    void testRerollSetOutcome() {
        DiceSet diceSet = new DiceSet();

        diceSet.setRollOutcome("skull, monkey, parrot, monkey, sword, sword, skull, coin");

        String message = diceSet.rerollSetOutcome("monkey, sword", "sword, diamond");
        System.out.println(message);
        Assertions.assertEquals(message, "");

        Assertions.assertEquals(diceSet.getDiceSet().get(1).getFigure(), "sword");
        Assertions.assertEquals(diceSet.getDiceSet().get(4).getFigure(), "diamond");

    }

    @Test
    @DisplayName("testUseTheSorceressCard")
    public void testUseTheSorceressCardInput() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("coin, diamond, coin, skull, skull, monkey, sword, sword");
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor playerDescriptor = new PlayerDescriptor();
        ScorePad scorePad = new ScorePad();
        playerDescriptor.setScorePad(scorePad);
        playerDescriptor.setDrawnFCard(new FCard("Coin", 0));
        playerDescriptorList.add(playerDescriptor);

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptor);
        boolean itWorked = messageProcessor.useTheSorceressCardInput("coin");
        Assertions.assertTrue(itWorked);
        diceSet.setRollOutcome("coin, diamond, coin, sword, sword, monkey, sword, sword");
        messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptor);
        itWorked = messageProcessor.useTheSorceressCardInput("coin");
        Assertions.assertFalse(itWorked);
    }

    @Test
    @DisplayName("messageTakeCard")
    void testTakeCard() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(interactingPlayerDescriptor);
        messageProcessor.turnOnRIGID();

        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.takeCard + " Sorceress");
        Assertions.assertEquals(toReturn.outMsg, "take.card Sorceress");
    }

    @Test
    @DisplayName("messageSetDice")
    void testSetDice() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();
        ScorePad scorePad = new ScorePad();
        interactingPlayerDescriptor.setScorePad(scorePad);
        playerDescriptorList.add(interactingPlayerDescriptor);

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(interactingPlayerDescriptor);
        messageProcessor.turnOnRIGID();

        messageProcessor.ProcessMessage(Commands.takeCard + " Sorceress");
        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.setDice + " skull, skull, monkey, parrot, " +
                "diamond, coin, sword, coin");
        Assertions.assertEquals(toReturn.outMsg,"outcome Sorceress, [skull, skull, monkey, parrot, diamond, coin," +
                " sword, coin], 300, the player got a score of 300 points from this dice set");
    }

    @Test
    @DisplayName("messageUseSorceress")
    void testUseSorceress() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();
        ScorePad scorePad = new ScorePad();
        interactingPlayerDescriptor.setScorePad(scorePad);
        playerDescriptorList.add(interactingPlayerDescriptor);

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(interactingPlayerDescriptor);
        messageProcessor.turnOnRIGID();

        messageProcessor.ProcessMessage(Commands.takeCard + " Sorceress");
        messageProcessor.ProcessMessage(Commands.setDice + " skull, skull, monkey, parrot, " +
                "diamond, coin, sword, coin");
        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.useSorceress + " monkey");
        Assertions.assertEquals(toReturn.outMsg,"use.sorceress, Sorceress, [monkey, skull, monkey, parrot, diamond, coin," +
                " sword, coin], 300, player use the sorceress card to roll one skull");
        toReturn = messageProcessor.ProcessMessage(Commands.useSorceress);
        Assertions.assertTrue(toReturn.success);
    }

    @Test
    @DisplayName("testRuleRollOneSkull")
    void testRuleRollOneSkull() {
        FCard fCard = new FCard("Diamond", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        RuleResult ruleResult = scoreEvaluator.ruleRollOneSkull(diceSet, fCard);
        Assertions.assertFalse(ruleResult.isPass());
        Assertions.assertEquals(ruleResult.getMessage(),"player tried to roll one skull but does not have the " +
                "sorceress card");
        fCard = new FCard("Sorceress", 0);
        diceSet = new DiceSet();
        ruleResult = scoreEvaluator.ruleRollOneSkull(diceSet, fCard);
        Assertions.assertTrue(ruleResult.isPass());
        Assertions.assertEquals(ruleResult.getMessage(),"player use the sorceress card to roll one skull");
    }

    @Test
    @DisplayName("testRuleGoToSkullIslandIf4Skulls")
    void testRuleGoToSkullIslandIf4Skulls() {
        FCard fCard = new FCard("Diamond", 0);
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("skull, skull, skull, monkey, parrot, parrot, sword, sword");
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        RuleResult ruleResult = scoreEvaluator.ruleGoToSkullIslandIf4Skulls(diceSet, fCard);
        Assertions.assertFalse(ruleResult.isPass());
        diceSet.setRollOutcome("skull, skull, skull, skull, parrot, parrot, sword, sword");
        scoreEvaluator = new ScoreEvaluator();
        ruleResult = scoreEvaluator.ruleGoToSkullIslandIf4Skulls(diceSet, fCard);
        Assertions.assertTrue(ruleResult.isPass());
        diceSet.setRollOutcome("skull, skull, skull, parrot, parrot, parrot, sword, sword");
        fCard = new FCard("1skull", 0);
        ruleResult = scoreEvaluator.ruleGoToSkullIslandIf4Skulls(diceSet, fCard);
        Assertions.assertTrue(ruleResult.isPass());
        diceSet.setRollOutcome("skull, skull, sword, parrot, parrot, parrot, sword, sword");
        fCard = new FCard("2skulls", 0);
        ruleResult = scoreEvaluator.ruleGoToSkullIslandIf4Skulls(diceSet, fCard);
        Assertions.assertTrue(ruleResult.isPass());
    }

    @Test
    @DisplayName("testHoldChest")
    void testHoldChest() {
        FCard fCard = new FCard("Chest", 0);
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("skull, skull, diamond, monkey, parrot, parrot, sword, sword");
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        RuleResult ruleResult = scoreEvaluator.ruleCanHoldDices(diceSet, fCard);
        Assertions.assertTrue(ruleResult.isPass());
        fCard = new FCard("Parrot", 0);
        ruleResult = scoreEvaluator.ruleCanHoldDices(diceSet, fCard);
        Assertions.assertFalse(ruleResult.isPass());
    }

    @Test
    @DisplayName("testHoldOrTakeOffSetOfDices")
    void testHoldOrTakeOffSetOfDices() {
        DiceSet diceSet = new DiceSet();

        diceSet.setRollOutcome("skull, monkey, parrot, monkey, sword, sword, skull, coin");

        String message = diceSet.holdOrTakeOffSetOfDices("parrot, monkey", true);
        Assertions.assertEquals(message, "");

        Assertions.assertFalse(diceSet.getDiceSet().get(6).getCanHold());
        Assertions.assertTrue(diceSet.getDiceSet().get(2).getCanHold());

        message = diceSet.holdOrTakeOffSetOfDices("parrot", false);
        Assertions.assertEquals(message, "");
        Assertions.assertFalse(diceSet.getDiceSet().get(2).getCanHold());
    }

    @Test
    @DisplayName("testHoldChestCommand")
    void testHoldChestCommand() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();
        ScorePad scorePad = new ScorePad();
        interactingPlayerDescriptor.setScorePad(scorePad);
        playerDescriptorList.add(interactingPlayerDescriptor);

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(interactingPlayerDescriptor);
        messageProcessor.turnOnRIGID();

        messageProcessor.ProcessMessage(Commands.setDice + " skull, skull, monkey, parrot, " +
                "diamond, coin, sword, coin");
        messageProcessor.ProcessMessage(Commands.takeCard + " Coin");
        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.holdChest + " diamond, coin");
        Assertions.assertTrue(toReturn.success);
        Assertions.assertEquals(toReturn.outMsg, "hold, Coin, [skull, skull, monkey, parrot, diamond, coin, " +
                "sword, coin], 0, dices cannot be hold");
        messageProcessor.ProcessMessage(Commands.takeCard + " Chest");
        toReturn = messageProcessor.ProcessMessage(Commands.holdChest);
        Assertions.assertTrue(toReturn.success);
        Assertions.assertEquals(toReturn.outMsg, "hold, Chest, [skull, skull, monkey, parrot, diamond, coin, " +
                "sword, coin], 0, choose to use the chest card but did not choose which dice to hold");
        toReturn = messageProcessor.ProcessMessage(Commands.holdChest + " diamond, coin");
        Assertions.assertTrue(toReturn.success);
        Assertions.assertEquals(toReturn.outMsg, "hold, Chest, [skull, skull, monkey, parrot, diamond, coin, " +
                "sword, coin], 0, dices can be hold");
    }

    @Test
    @DisplayName("testTakeOutChestCommand")
    void testTakeOutChestCommand() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();
        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();
        ScorePad scorePad = new ScorePad();
        interactingPlayerDescriptor.setScorePad(scorePad);
        playerDescriptorList.add(interactingPlayerDescriptor);

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        messageProcessor.setInteractingPlayerDescriptor(interactingPlayerDescriptor);
        messageProcessor.turnOnRIGID();

        messageProcessor.ProcessMessage(Commands.setDice + " skull, skull, monkey, parrot, " +
                "diamond, coin, sword, coin");
        messageProcessor.ProcessMessage(Commands.takeCard + " Chest");
        PostStatus toReturn = messageProcessor.ProcessMessage(Commands.holdChest + " diamond, coin");
        Assertions.assertTrue(toReturn.success);
        Assertions.assertEquals(toReturn.outMsg, "hold, Chest, [skull, skull, monkey, parrot, diamond, coin, " +
                "sword, coin], 0, dices can be hold");
        messageProcessor.ProcessMessage(Commands.takeCard + " Chest");
        toReturn = messageProcessor.ProcessMessage(Commands.takeOutChest + " diamond, coin");
        Assertions.assertTrue(toReturn.success);
        Assertions.assertEquals(toReturn.outMsg, "takeOut, Chest, [skull, skull, monkey, parrot, diamond, coin, " +
                "sword, coin], 0, dices can be hold");
    }

    @Test
    @DisplayName("testGetScoreWhenDiceHold")
    void testGetScoreWhenDiceHold() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("coin, diamond, coin, diamond, skull, skull, diamond, coin");
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.holdOrTakeOffSetOfDices("coin, diamond, coin, diamond", true);

        int score = scoreEvaluator.getScoreWhenDiceHold(fCard, diceSet);

        Assertions.assertEquals(score, 600);
    }

    @Test
    @DisplayName("testRuleSkullIsland")
    void testRuleSkullIsland() {
        FCard fCard = new FCard("Diamond", 0);
        DiceSet diceSet = new DiceSet();
        diceSet.setRollOutcome("skull, skull, skull, monkey, parrot, parrot, sword, sword");
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        RuleResult ruleResult = scoreEvaluator.ruleSkullIsland(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), -300);
        fCard = new FCard("2skulls", 0);
        ruleResult = scoreEvaluator.ruleSkullIsland(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), -500);
        fCard = new FCard("1skull", 0);
        ruleResult = scoreEvaluator.ruleSkullIsland(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), -400);
        fCard = new FCard("Captain", 0);
        ruleResult = scoreEvaluator.ruleSkullIsland(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), -600);
    }
    @Test
    @DisplayName("testSubtractOtherPlayersScore")
    void testSubtractOtherPlayersScore() {
        FCardDeck fCardDeck = new FCardDeck();
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser,
                playerDescriptorList);
        PlayerDescriptor interactingPlayerDescriptor = new PlayerDescriptor();
        interactingPlayerDescriptor.setDrawnFCard(new FCard("Coin", 0));
        interactingPlayerDescriptor.setScorePad(new ScorePad());
        interactingPlayerDescriptor.getScorePad().setTotalScore(1500);
        diceSet = new DiceSet();

        diceSet.setRollOutcome("skull, skull, skull, skull, skull, diamond, coin, sword");

        PlayerDescriptor playerDescriptor = new PlayerDescriptor();
        playerDescriptor.setScorePad(new ScorePad());
        playerDescriptor.getScorePad().setTotalScore(1000);
        playerDescriptorList.add(playerDescriptor);
        playerDescriptorList.add(interactingPlayerDescriptor);
        playerDescriptor = new PlayerDescriptor();
        playerDescriptor.setScorePad(new ScorePad());
        playerDescriptor.getScorePad().setTotalScore(2000);
        playerDescriptorList.add(playerDescriptor);

        playerDescriptorList = messageProcessor.subtractOtherPlayersScore(playerDescriptorList,
                interactingPlayerDescriptor, diceSet);

        Assertions.assertEquals(playerDescriptorList.get(0).getScorePad().getTotalScore(), 500);
        Assertions.assertEquals(playerDescriptorList.get(1).getScorePad().getTotalScore(), 1500);
        Assertions.assertEquals(playerDescriptorList.get(2).getScorePad().getTotalScore(), 1500);
    }

    @Test
    @DisplayName("testRuleLeaveSkullIsland")
    void testRuleLeaveSkullIsland() {
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("1skull", 0);
        diceSet.setRollOutcome("skull, skull, skull, skull, monkey, monkey, sword, sword");

        RuleResult ruleResult = scoreEvaluator.ruleLeaveSkullIsland(diceSet, fCard);
        Assertions.assertFalse(ruleResult.isPass());

        scoreEvaluator.ruleGoToSkullIslandIf4Skulls(diceSet, fCard);
        ruleResult = scoreEvaluator.ruleLeaveSkullIsland(diceSet, fCard);
        Assertions.assertTrue(ruleResult.isPass());

        diceSet.setRollOutcome("skull, skull, skull, skull, skull, skull, sword, sword");
        ruleResult = scoreEvaluator.ruleLeaveSkullIsland(diceSet, fCard);
        Assertions.assertFalse(ruleResult.isPass());
    }
}
