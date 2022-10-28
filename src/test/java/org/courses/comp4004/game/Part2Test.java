package org.courses.comp4004.game;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2Test {

    private static String path = "C:\\Users\\miro\\Documents\\Michael\\Projects\\pirates\\game\\logs";

    PrintStream log;

    @BeforeAll
    static void preparePaths() throws IOException {
        Path p = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString() + File.separator + "logs");
        if(!Files.exists(p)){
            path =  Files.createDirectory(p).toString();
        }else{
            path = p.toString();
        }
    }
    @BeforeEach
    void createLogFile(TestInfo testInfo) throws FileNotFoundException {
        String line;
        PostStatus postStatus;

        log = new PrintStream(new FileOutputStream(path + "\\" + testInfo.getDisplayName()));
    }

    @AfterEach
    void closeLogFile() throws IOException {
        log.flush();
        log.close();
    }

    // Row77Test	      roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
    @Test
    @DisplayName("row77")
    void row77() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("Sorceress", 0);
        diceSet.setRollOutcome("diamond, diamond, sword, monkey, coin, parrot, parrot, parrot");

        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Sorceress");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice diamond, diamond, sword, monkey, coin, parrot, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Sorceress, [diamond, diamond, sword, monkey, coin, parrot, parrot, parrot], 400, the player got a score of 400 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice diamond, diamond, sword, monkey, coin, skull, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Sorceress, [diamond, diamond, sword, monkey, coin, skull, monkey, monkey], 400, the player got a score of 400 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "use.sorceress monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "use.sorceress, Sorceress, [diamond, diamond, sword, monkey, coin, monkey, monkey, monkey], 500, player use the sorceress card to roll one skull");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    // Row78Test	      roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
    @Test
    @DisplayName("row78")
    void row78() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("Sorceress", 0);
        diceSet.setRollOutcome("skull, skull, skull, parrot, parrot, parrot, sword, sword");

        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Sorceress");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, parrot, parrot, parrot, sword, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Sorceress, [parrot, skull, skull, parrot, parrot, parrot, sword, sword], 200, player got 3 skull but reroll one skull since got one Sorceress card");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice parrot, skull, skull, parrot, parrot, parrot, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Sorceress, [parrot, skull, skull, parrot, parrot, parrot, parrot, parrot], 1000, the player got a score of 1000 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    // Row79Test	      roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
    @Test
    @DisplayName("row79")
    void row79() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("Sorceress", 0);

        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Sorceress");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, parrot, parrot, parrot, parrot, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Sorceress, [skull, parrot, parrot, parrot, parrot, monkey, monkey, monkey], 300, the player got a score of 300 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, parrot, parrot, parrot, parrot, skull, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Sorceress, [skull, parrot, parrot, parrot, parrot, skull, parrot, parrot], 1000, the player got a score of 1000 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "use.sorceress parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "use.sorceress, Sorceress, [parrot, parrot, parrot, parrot, parrot, skull, parrot, parrot], 2000, player use the sorceress card to roll one skull");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    // Row82Test	      first roll gets 3 monkeys 3 parrots  1 skull 1 coin  SC = 1100  (i.e., sequence of of 6 + coin)
    @Test
    @DisplayName("row82")
    void row82() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("Monkey&Parrot", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, monkey, parrot, parrot, parrot, skull, coin");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 1100);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row83Test         over several rolls: 2 monkeys, 1 parrot, 2 coins, 1 diamond, 2 swords         SC 400
    @Test
    @DisplayName("row83")
    void row83() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("Monkey&Parrot", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, sword, sword, coin, diamond, parrot, diamond, coin");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("monkey, monkey, parrot, coin, coin, sword, diamond, diamond");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("monkey, monkey, parrot, coin, coin, sword, sword, diamond");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row84Test        over several rolls get 3 monkeys, 4 parrots, 1 sword    SC 2000 (ie seq of 7)
    @Test
    @DisplayName("row84")
    void row84() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("Monkey&Parrot", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("diamond, sword, sword, sword, diamond, parrot, monkey, coin");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("monkey, monkey, parrot, sword, coin, parrot, parrot, coin");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("monkey, monkey, parrot, monkey, coin, parrot, parrot, diamond");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("monkey, monkey, parrot, monkey, sword, parrot, parrot, parrot");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 2000);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    @Test
    @DisplayName("row87")
    void row87() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("Chest", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Chest");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice parrot, parrot, parrot, sword, sword, diamond, diamond, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Chest, [parrot, parrot, parrot, sword, sword, diamond, diamond, coin], 400, the player got a score of 400 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "hold diamond, diamond, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice parrot, parrot, parrot, parrot, parrot, diamond, diamond, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "hold parrot, parrot, parrot, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "takeOut diamond, diamond, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice parrot, parrot, parrot, parrot, parrot, skull, coin, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Chest, [parrot, parrot, parrot, parrot, parrot, skull, coin, parrot], 1100, the player got a score of 1100 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
    }


}
