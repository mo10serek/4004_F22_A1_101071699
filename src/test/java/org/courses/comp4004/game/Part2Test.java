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

    @Test
    @DisplayName("row92")
    void row92() {
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
        line = "set.dice skull, skull, parrot, parrot, parrot, coin, coin, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Chest, [skull, skull, parrot, parrot, parrot, coin, coin, coin], 500, the player got a score of 500 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "hold coin, coin, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, diamond, diamond, coin, coin, coin, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "hold coin";
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, coin, coin, coin, coin, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Chest, [skull, skull, skull, coin, coin, coin, coin, coin], 600, the player died but still score points");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row97")
    void row97() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("Coin", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, monkey, sword, sword, sword, diamond, parrot");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 400);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    @Test
    @DisplayName("row98")
    void row98() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("Captain", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, monkey, sword, sword, sword, coin, coin");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 1800);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    @Test
    @DisplayName("row99")
    void row99() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("Coin", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, monkey, sword, sword, sword, sword, diamond");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 1000);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));

    }

    @Test
    @DisplayName("row103")
    void row103() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("Monkey&Parrot", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, parrot, coin, coin, diamond, diamond, diamond");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 1200);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    @Test
    @DisplayName("row106")
    void row106() {
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

        FCard fCard = new FCard("2skulls", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, sword, sword, sword, sword, sword, sword, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2skulls, [skull, sword, sword, sword, sword, sword, sword, sword], 0, Player {0, number, integer} died.");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row107")
    void row107() {
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

        FCard fCard = new FCard("1skull", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, sword, sword, sword, sword, sword, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 1skull, [skull, skull, sword, sword, sword, sword, sword, sword], 0, Player died.");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row108")
    void row108() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("2skulls", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card 2skulls");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, parrot, parrot, parrot, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2skulls, [skull, skull, parrot, parrot, parrot, monkey, monkey, monkey], 0, -400, -400, player got 4 skulls in first roll and need to go to skull Island");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, skull, sword, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2skulls, [skull, skull, skull, skull, sword, monkey, monkey, monkey], 0, -600, -600, player got more skull so subtracts other players");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, skull, skull, skull, skull, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2skulls, [skull, skull, skull, skull, skull, skull, skull, sword], 0, -900, -900, player got more skull so subtracts other players");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row110")
    void row110() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("Captain", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Captain");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, skull, skull, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Captain, [skull, skull, skull, skull, skull, monkey, monkey, monkey], 0, -1000, -1000, player got 4 skulls in first roll and need to go to skull Island");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, skull, skull, skull, skull, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome Captain, [skull, skull, skull, skull, skull, skull, skull, coin], 0, -1400, -1400, player got more skull so subtracts other players");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row111")
    void row111() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("2skulls", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card 2skulls");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, sword, sword, sword, sword, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2skulls, [skull, skull, skull, sword, sword, sword, sword, sword], 0, -500, -500, player got 4 skulls in first roll and need to go to skull Island");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, coin, coin, coin, coin, coin";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2skulls, [skull, skull, skull, coin, coin, coin, coin, coin], 0, -500, -500, player did not get any more skulls so his turn ends");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row114")
    void row114() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("2swords", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2swords you are in sea battle");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, monkey, skull, skull, skull, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        System.out.println(postStatus.outMsg);
        Assertions.assertEquals(postStatus.outMsg,"outcome 2swords, [monkey, monkey, monkey, monkey, skull, skull, skull, sword], -300, 0, 0, did not got the bonus so the player looses points and end his turn");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row115")
    void row115() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("3swords", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 3swords you are in sea battle");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice sword, sword, skull, skulls, parrot, parrot, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome 3swords, [sword, sword, skull, skulls, parrot, parrot, parrot, parrot], 200, 0, 0, did not receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice sword, sword, skull, skull, skull, skull, skull, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome 3swords, [sword, sword, skull, skull, skull, skull, skull, skull], -500, 0, 0, did not got the bonus so the player looses points and end his turn");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row117")
    void row117() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("2swords", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2swords you are in sea battle");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, sword, sword, coin, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome 2swords, [monkey, monkey, monkey, sword, sword, coin, parrot, parrot], 500, 0, 0, receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row118")
    void row118() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("2swords", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2swords you are in sea battle");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, monkey, sword, skull, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2swords, [monkey, monkey, monkey, monkey, sword, skull, parrot, parrot], 200, 0, 0, did not receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, monkey, sword, skull, sword, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 2swords, [monkey, monkey, monkey, monkey, sword, skull, sword, skull], 500, 0, 0, receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row120")
    void row120() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("3swords", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 3swords you are in sea battle");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, sword, sword, sword, sword, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 3swords, [monkey, monkey, monkey, sword, sword, sword, sword, skull], 800, 0, 0, receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row121")
    void row121() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("3swords", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 3swords you are in sea battle");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, monkey, sword, sword, skull, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 3swords, [monkey, monkey, monkey, monkey, sword, sword, skull, skull], 200, 0, 0, did not receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, sword, sword, sword, sword, skull, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 3swords, [skull, skull, sword, sword, sword, sword, skull, skull], -500, 0, 0, did not got the bonus so the player looses points and end his turn");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row123")
    void row122() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("4swords", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 4swords you are in sea battle");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, sword, sword, sword, sword, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 4swords, [monkey, monkey, monkey, sword, sword, sword, sword, skull], 1300, 0, 0, receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    @Test
    @DisplayName("row124")
    void row124() {
        DiceSet diceSet = new DiceSet();
        FCardDeck fCardDeck = new FCardDeck();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();
        LineParser lineParser = new LineParser();

        PrintWriter out =  new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<PlayerDescriptor> playerDescriptorList = new ArrayList<>();
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));
        playerDescriptorList.add(new PlayerDescriptor(new PlayerStreams(out, in), new ScorePad()));

        MessageProcessor messageProcessor = new MessageProcessor(fCardDeck, diceSet, scoreEvaluator, lineParser, playerDescriptorList);
        messageProcessor.turnOnRIGID();
        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));

        String line;
        PostStatus postStatus;

        FCard fCard = new FCard("4swords", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 4swords you are in sea battle");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, sword, skull, diamond, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 4swords, [monkey, monkey, monkey, sword, skull, diamond, parrot, parrot], 200, 0, 0, did not receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, sword, skull, diamond, sword, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 4swords, [monkey, monkey, monkey, sword, skull, diamond, sword, sword], 300, 0, 0, did not receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice sword, parrot, parrot, sword, skull, diamond, sword, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "outcome 4swords, [sword, parrot, parrot, sword, skull, diamond, sword, sword], 1300, 0, 0, receive correct number of swords");
        log.println(line);
        log.println(postStatus.outMsg);
    }
}