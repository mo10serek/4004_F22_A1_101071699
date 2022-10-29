package org.courses.comp4004.game;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part3Test {

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
        log = new PrintStream(new FileOutputStream(path + "\\" + testInfo.getDisplayName()));
    }

    @AfterEach
    void closeLogFile() throws IOException {
        log.flush();
        log.close();
    }
    // Row130Test	      player1 rolls 7 swords + 1 skull with FC = captain (gets 4000 points - could win)
    @Test
    @DisplayName("row130")
    void row130() {
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

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));
        FCard fCard = new FCard("Captain", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Captain");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice sword, sword, sword, sword, sword, sword, sword, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Captain, [sword, sword, sword, sword, sword, sword, sword, skull], 4000, 0, 0, the player got a score of 4000 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(1));
        fCard.setFigure("1skull");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card 1skull");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice sword, sword, sword, sword, sword, sword, sword, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome 1skull, [sword, sword, sword, sword, sword, sword, sword, skull], 4000, 2000, 0, the player got a score of 2000 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(2));
        fCard.setFigure("Coin");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Coin");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, monkey, monkey, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Coin, [skull, skull, skull, monkey, monkey, monkey, monkey, monkey], 4000, 2000, 0, Player died.");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    // Row134Test	      scenario to show player1 rolls 7 swords + 1 skull with FC = captain (gets 4000 points - could win)
    @Test
    @DisplayName("row134")
    void row134() {
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

        String line;
        PostStatus postStatus;

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));
        FCard fCard = new FCard("Captain", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Captain");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice sword, sword, sword, sword, sword, sword, sword, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Captain, [sword, sword, sword, sword, sword, sword, sword, skull], 4000, 0, 0, the player got a score of 4000 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(1));
        fCard.setFigure("Coin");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Coin");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, monkey, monkey, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Coin, [skull, skull, skull, monkey, monkey, monkey, monkey, monkey], 4000, 0, 0, Player died.");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(2));
        fCard.setFigure("Captain");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Captain");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, skull, skull, skull, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Captain, [skull, skull, skull, skull, skull, skull, parrot, parrot], 2800, -1200, 0, player got 4 skulls in first roll and need to go to skull Island");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));
        fCard.setFigure("Coin");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Coin");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice monkey, monkey, monkey, monkey, parrot, parrot, parrot, parrot";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Coin, [monkey, monkey, monkey, monkey, parrot, parrot, parrot, parrot], 3800, -1200, 0, the player got a score of 1000 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(1));
        fCard.setFigure("Captain");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Captain");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, monkey, monkey, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Captain, [skull, skull, skull, monkey, monkey, monkey, monkey, monkey], 3800, -1200, 0, Player died.");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(2));
        fCard.setFigure("1skull");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card 1skull");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, monkey, monkey, monkey, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome 1skull, [skull, skull, monkey, monkey, monkey, monkey, monkey, monkey], 3800, -1200, 0, Player died.");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);
    }

    // Row142Test	      player 1 scores 0 then
    @Test
    @DisplayName("row142")
    void row142() {
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

        String line;
        PostStatus postStatus;

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));
        FCard fCard = new FCard("Captain", 0);
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Captain");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, skull, skull, monkey, monkey, monkey, monkey, monkey";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Captain, [skull, skull, skull, monkey, monkey, monkey, monkey, monkey], 0, 0, 0, Player died.");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(1));
        fCard.setFigure("Captain");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Captain");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice sword, sword, sword, sword, sword, sword, sword, skull";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Captain, [sword, sword, sword, sword, sword, sword, sword, skull], 0, 4000, 0, the player got a score of 4000 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "done";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"the player end his turn");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(2));
        fCard.setFigure("2skulls");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card 2skulls");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice skull, sword, sword, sword, sword, sword, sword, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome 2skulls, [skull, sword, sword, sword, sword, sword, sword, sword], 0, 4000, 0, Player died.");
        log.println(line);
        log.println(postStatus.outMsg);

        messageProcessor.setInteractingPlayerDescriptor(playerDescriptorList.get(0));
        fCard.setFigure("Captain");
        line = "take.card " + fCard.getFigure();
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg, "take.card Captain");
        log.println(line);
        log.println(postStatus.outMsg);
        line = "set.dice sword, sword, sword, sword, sword, sword, sword, sword";
        postStatus = messageProcessor.ProcessMessage(line);
        Assertions.assertEquals(postStatus.outMsg,"outcome Captain, [sword, sword, sword, sword, sword, sword, sword, sword], 9000, 4000, 0, the player got a score of 9000 points from this dice set");
        log.println(line);
        log.println(postStatus.outMsg);
    }
}
