package org.courses.comp4004.game;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unit test for Part1Test.
 * 44 | PART 1: getting first50 marks    (SINGLE PLAYER SCORING)
 */
public class Part1Test {

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

    // Row45Test	die with 3 skulls on first roll
    @Test
    @DisplayName("row45")
    void row45() {
        DiceSet diceSet = new DiceSet();
        FCard fCard = new FCard("Coin", 0);
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, skull, skull, sword, sword, sword, sword, sword");
        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);

        Assertions.assertTrue(ruleResult.isPass());
        Assertions.assertEquals(ruleResult.getScore(), 0);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row46Test	roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll 3 swords, get 2 skulls 1 sword  die
    @Test
    @DisplayName("row46")
    void row46() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, sword, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 400);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, skull, skull, sword");
        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), 0);
        Assertions.assertTrue(ruleResult.isPass());
        log.println(diceSet);
        log.println(ruleResult.getMessage());
    }

    // Row47Test	roll 2 skulls, 4 parrots, 2 swords, hold parrots, reroll swords, get 1 skull 1 sword  die
    @Test
    @DisplayName("row47")
    void row47() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, skull, parrot, parrot, parrot, parrot, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 300);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("skull, skull, parrot, parrot, parrot, parrot, skull, sword");
        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), 0);
        Assertions.assertTrue(ruleResult.isPass());
        log.println(diceSet);
        log.println(ruleResult.getMessage());
    }

    // Row48Test	roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll swords, get 1 skull 2 monkeys
    @Test
    @DisplayName("row48")
    void row48() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, sword, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 400);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, skull, monkey, monkey");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 300);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));

        diceSet.setRollOutcome("skull, parrot, parrot, parrot, parrot, skull, skull, monkey");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 0);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));

        RuleResult ruleResult = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, fCard);
        Assertions.assertEquals(ruleResult.getScore(), 0);
        Assertions.assertTrue(ruleResult.isPass());
        log.println(diceSet);
        log.println(ruleResult.getMessage());
    }

    // Row50Test	roll 1 skull, 2 parrots, 3 swords, 2 coins, reroll parrots get 2 coins
    @Test
    @DisplayName("row50")
    void row50() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, parrot, parrot, sword, sword, sword, coin, coin");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 500);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));

        diceSet.setRollOutcome("skull, coin, coin, sword, sword, sword, coin, coin");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 1100);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));

        diceSet.setRollOutcome("skull, coin, coin, coin, coin, coin, coin, coin");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 4800);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row52Test	score first roll with nothing but 2 diamonds and 2 coins and FC is captain (SC 800)
    @Test
    @DisplayName("row52")
    void row52() {
        FCard fCard = new FCard("Captain", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, parrot, parrot, diamond, diamond, coin, coin");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 800);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row53Test	get set of 2 monkeys on first roll, get 3rd monkey on 2nd roll (SC 200 since FC is coin)
    @Test
    @DisplayName("row53")
    void row53() {
        FCard fCard = new FCard("Coin", 100);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, skull, skull, sword, sword, parrot, parrot");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 100);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));

        diceSet.setRollOutcome("monkey, monkey, skull, skull, sword, sword, sword, monkey");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 300);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row54Test	score 2 sets of 3 (monkey, swords) in RTS on first roll   (SC 300)
    @Test
    @DisplayName("row54")
    void row54() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, monkey, sword, sword, sword, skull, skull");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 300);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

}
