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

    // Row55Test	score 2 sets of 3 (monkey, parrots) in RTS using 2 rolls   (SC 300)
    @Test
    @DisplayName("row55")
    void row55() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("diamond, diamond, diamond, skull, skull, monkey, sword, parrot");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 500);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row56Test	score a set of 3 diamonds correctly (i.e., 400 points)   (SC 500)
    @Test
    @DisplayName("row56")
    void row56() {
        FCard fCard = new FCard("Diamond", 100);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("coin, coin, coin, coin, skull, skull, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 700);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row57Test	score a set of 4 coins correctly (i.e., 200 + 400 points) with FC is a diamond (SC 700)
    @Test
    @DisplayName("row57")
    void row57() {
        FCard fCard = new FCard("Diamond", 100);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("sword, sword, sword, parrot, parrot, parrot, parrot, skull");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 400);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row58Test	score set of 3 swords and set of 4 parrots correctly on first roll (SC 400 because of FC)
    @Test
    @DisplayName("row58")
    void row58() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, coin, coin, parrot, parrot, sword, sword, sword");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("skull, coin, coin, coin, sword, sword, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 800);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row59Test	score set of 3 coins+ FC and set of 4 swords correctly over several rolls (SC = 200+400+200 = 800)
    @Test
    @DisplayName("row59")
    void row59() {
        FCard fCard = new FCard("Captain", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, coin, coin, parrot, parrot, sword, sword, sword");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("skull, coin, coin, coin, sword, sword, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 1200);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row60Test	same as previous row but with captain fortune card  (SC = (100 + + 300 + 200)*2 = 1200)
    @Test
    @DisplayName("row60")
    void row60() {
        FCard fCard = new FCard("Coin", 0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, monkey, monkey, parrot, parrot, sword, sword, sword");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("skull, skull, sword, parrot, parrot, sword, sword, sword");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("skull, skull, sword, sword, monkey, sword, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 600);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row62Test	score set of 6 monkeys on first roll (SC 1100)
    @Test
    @DisplayName("row62")
    void row62() {
        FCard fCard = new FCard("Diamond", 100);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, monkey, monkey, monkey, monkey, parrot, parrot");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 1100);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row63Test	score set of 7 parrots on first roll (SC 2100)
    @Test
    @DisplayName("row63")
    void row63() {
        FCard fCard = new FCard("Diamond", 100);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("parrot, parrot, parrot, parrot, parrot, parrot, parrot, skull");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 2100);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row64Test	score set of 8 coins on first roll (SC 5400)  seq of 8 + 9 coins(FC is coin) +  full chest
    @Test
    @DisplayName("row64")
    void row64() {
        FCard fCard = new FCard("Coin", 100);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("coin, coin, coin, coin, coin, coin, coin, coin");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 5400);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row65Test	score set of 8 coins on first roll and FC is diamond (SC 5400)
    @Test
    @DisplayName("row65")
    void row65() {
        FCard fCard = new FCard("Diamond", 100);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("coin, coin, coin, coin, coin, coin, coin, coin");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 5400);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row66Test	score set of 8 swords on first roll and FC is captain (SC 4500x2 = 9000) since full chest
    @Test
    @DisplayName("row66")
    void row66() {
        FCard fCard = new FCard("Captain", 00);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("sword, sword, sword, sword, sword, sword, sword, sword");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 9000);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row67Test	score set of 8 monkeys over several rolls (SC 4600 because of FC is coin and full chest)
    @Test
    @DisplayName("row67")
    void row67() {
        FCard fCard = new FCard("Coin",0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, monkey, monkey, monkey, monkey, sword, sword");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("monkey, monkey, monkey, monkey, monkey, monkey, monkey, monkey");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 4600);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row68Test	score a set of 2 diamonds over 2 rolls with FC is diamond (SC 400)
    @Test
    @DisplayName("row68")
    void row68() {
        FCard fCard = new FCard("Diamond",0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, skull, skull, sword, sword, parrot, parrot");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("monkey, monkey, skull, skull, sword, sword, diamond, diamond");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 400);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row69Test	score a set of 3 diamonds over 2 rolls (SC 500)
    @Test
    @DisplayName("row69")
    void row69() {
        FCard fCard = new FCard("Coin",0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, skull, skull, sword, sword, diamond, parrot");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("diamond, diamond, skull, skull, sword, sword, diamond, parrot");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 500);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row70Test	score a set of 3 coins over 2 rolls  (SC 600)
    @Test
    @DisplayName("row70")
    void row70() {
        FCard fCard = new FCard("Coin",0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, coin, coin, monkey, parrot, sword, sword, sword");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("skull, coin, coin, monkey, parrot, coin, monkey, parrot");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 600);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row71Test	score a set of 3 coins over 2 rolls  with FC is diamond (SC 500)
    @Test
    @DisplayName("row71")
    void row71() {
        FCard fCard = new FCard("Diamond",0);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("skull, coin, coin, monkey, parrot, sword, sword, sword");
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
        diceSet.setRollOutcome("skull, coin, coin, monkey, parrot, coin, monkey, parrot");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 500);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

    // Row72Test	score a set of 4 monkeys and a set of 3 coins (including the COIN fortune card) (SC 600)
    @Test
    @DisplayName("row72")
    void row72() {
        FCard fCard = new FCard("Coin", 100);
        DiceSet diceSet = new DiceSet();
        ScoreEvaluator scoreEvaluator = new ScoreEvaluator();

        diceSet.setRollOutcome("monkey, monkey, monkey, monkey, coin, coin, skull, skull");
        Assertions.assertEquals(scoreEvaluator.getScore(fCard, diceSet), 600);
        log.println(diceSet);
        log.println(scoreEvaluator.getScore(fCard, diceSet));
    }

}
