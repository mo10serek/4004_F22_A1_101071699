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

    


}
