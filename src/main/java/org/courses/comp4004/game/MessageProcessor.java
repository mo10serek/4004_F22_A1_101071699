package org.courses.comp4004.game;

import java.util.List;

import static java.util.Objects.isNull;

public class MessageProcessor {
    private FCardDeck fCardDeck = null;
    private DiceSet diceSet = null;
    private ScoreEvaluator scoreEvaluator = null;
    private PlayerDescriptor interactingPlayerDescriptor = null;
    private LineParser lineParser = null;
    private List<PlayerDescriptor> playerDescriptorList  = null;
    private boolean RIGID = false;


    public void turnOnRIGID() {
        RIGID = !RIGID;
    }

    public boolean getRIGID() { return RIGID; }

    public PlayerDescriptor getInteractingPlayerDescriptor() {
        return interactingPlayerDescriptor;
    }

    public void setInteractingPlayerDescriptor(PlayerDescriptor interactingPlayerDescriptor) {
        this.interactingPlayerDescriptor = interactingPlayerDescriptor;
    }

    public MessageProcessor(FCardDeck fCardDeck, DiceSet diceSet, ScoreEvaluator scoreEvaluator, LineParser lineParser, List<PlayerDescriptor> playerDescriptorList) {
        this.fCardDeck = fCardDeck;
        this.diceSet = diceSet;
        this.scoreEvaluator = scoreEvaluator;
        this.lineParser = lineParser;
        this.playerDescriptorList = playerDescriptorList;
    }
    private String msg = null;
    public PostStatus ProcessMessage(String line) {
        PostStatus toReturn = null;
        boolean success = true;
        if(!isNull(line)) {
            lineParser.init(line);
            String cmd = lineParser.getCmd();
            if (RIGID) {
                if (cmd.equalsIgnoreCase(Commands.takeCard)) {
                    FCard fCard = fCardDeck.draw(lineParser.getParmsLine()); //fCardDeck.draw();
                    interactingPlayerDescriptor.setDrawnFCard(fCard);
                    String fCardFigure = fCard.getFigure();
                    toReturn = new PostStatus(Commands.takeCard + " " + fCardFigure, true);
                } else if (cmd.equalsIgnoreCase(Commands.setDice)) {
                    diceSet.setRollOutcome(lineParser.getParmsLine());
                    toReturn = scoreNormal();
                }
            }
            if (cmd.equalsIgnoreCase(Commands.modeInteracting)) {//"mode.interacting"; //  <null>

                toReturn = new PostStatus(Commands.modeInteracting + "", true);

            } else if(cmd.equalsIgnoreCase(Commands.modeServerBroadcasting)) {//"mode.broadcasting"; //  <null>

                // only for server but not used
                toReturn = new PostStatus(Commands.modeServerBroadcasting + "", true);

            }else if(cmd.equalsIgnoreCase(Commands.inform)) {//"inform"; //  <msg> (Information send to player)

                toReturn = new PostStatus(Commands.inform + "", true);

            }else if(cmd.equalsIgnoreCase(Commands.draw)) {//"draw"; //  <null> (player requests drawing the card form Fortune Card deck)

                FCard fCard = fCardDeck.draw(); //fCardDeck.draw();
                interactingPlayerDescriptor.setDrawnFCard(fCard);
                String fCardFigure = fCard.getFigure();
                toReturn = new PostStatus(Commands.outcome + " " + fCardFigure, true);
            }else if(cmd.equalsIgnoreCase(Commands.roll)) {
                if (interactingPlayerDescriptor.getDrawnFCard() == null) {
                    msg = ", the player haven't drawn a card. Please draw a card by writing the command \'draw\'";
                    toReturn = new PostStatus(Commands.roll + msg,true);
                } else {
                    if (lineParser.getParmsLine().length() == 0) {  //"roll"; //  <null> (roll the whole DiceSet)
                        diceSet.roll();
                    } else { //"roll"; //  <Dice1>,<Dice2>,...,<DiceN>   (player request rolling SELECTED SUBSET OF DICE)
                        diceSet.roll(lineParser.getParmsLine());
                    }
                }
            }else if (cmd.equalsIgnoreCase(Commands.useSorceress)) {
                RuleResult useSorceress = scoreEvaluator.ruleRollOneSkull(diceSet,
                        interactingPlayerDescriptor.getDrawnFCard());
                if (useSorceress.isPass()) {
                    if (RIGID) {
                        useTheSorceressCardInput(lineParser.getParmsLine());
                    } else {
                        useTheSorceressCard();
                    }
                }
                interactingPlayerDescriptor.getScorePad().addScore(
                        scoreEvaluator.getScore(interactingPlayerDescriptor.getDrawnFCard(), diceSet));
                msg = useSorceress.getMessage();

            }else if (cmd.equalsIgnoreCase(Commands.holdChest)) {
                    RuleResult holdDiceRule = scoreEvaluator.ruleCanHoldDices(diceSet,
                            interactingPlayerDescriptor.getDrawnFCard());
                    if (holdDiceRule.isPass()) {
                        if (lineParser.getParmsLine().length() != 0) {
                            diceSet.holdOrTakeOffSetOfDices(lineParser.getParmsLine(), true);
                            msg = holdDiceRule.getMessage();
                        } else {
                            msg = "choose to use the chest card but did not choose which dice to hold";
                        }
                    } else {
                        msg = holdDiceRule.getMessage();
                    }
            }else if (cmd.equalsIgnoreCase(Commands.help)) {
                toReturn = new PostStatus(Commands.outcome + " list of command to use " +
                        " , outcome: notify the player of what is the current card, dice set and all of the players scores" +
                        " , draw: allow the player to pick the card from the pile" +
                        " , roll: allow the player to roll a set of dice or pick which dice to roll from" +
                        " , <Dice1>,<Dice2>,...,<DiceN>   (player request rolling SELECTED SUBSET OF DICE)" +
                        " , done: the player end their turn" +
                        " , use.sorceress: to allow the player have the sorceress card",
                        true);
            }
            if (!cmd.equalsIgnoreCase(Commands.help) &&
                    !cmd.equalsIgnoreCase(Commands.setDice) && !cmd.equalsIgnoreCase(Commands.takeCard) &&
                    !cmd.equalsIgnoreCase(Commands.roll) && !cmd.equalsIgnoreCase(Commands.draw) &&
                    !cmd.equalsIgnoreCase(Commands.modeInteracting) &&
                    !cmd.equalsIgnoreCase(Commands.modeServerBroadcasting) && !cmd.equalsIgnoreCase(Commands.inform) &&
                    !msg.contains("unknown command")) {
                toReturn = new PostStatus(cmd
                        + ", " + interactingPlayerDescriptor.getDrawnFCard().getFigure()
                        + ", " + diceSet.toString()
                        + ", " + interactingPlayerDescriptor.getScorePad().getTotalScore()
                        + ", " + msg,
                        success);
            }
        } else {
            msg = "unknown command";
            toReturn = new PostStatus(msg, true);
        }

        return toReturn;
    }

    public boolean useTheSorceressCard() {
        boolean success = false;
        for (int counter = 0; counter < diceSet.getMAX_SIZE(); counter++) {
            if (diceSet.getDiceSet().get(counter).getFigure().contains("skull")) {
                diceSet.getDiceSet().get(counter).enableRollIfFigure("skull");
                diceSet.reroll("skull");
                success = true;
                break;
            }
        }
        return success;
    }

    public boolean useTheSorceressCardInput(String input) {
        boolean success = false;
        for (int counter = 0; counter < diceSet.getMAX_SIZE(); counter++) {
            if (diceSet.getDiceSet().get(counter).getFigure().contains("skull")) {
                diceSet.getDiceSet().get(counter).enableRollIfFigure("skull");
                if (lineParser.getParmsLine().length() == 0) {
                    diceSet.reroll("skull");
                } else {
                    diceSet.rerollSetOutcome("skull", input);
                }
                success = true;
                break;
            }
        }
        return success;
    }

    public PostStatus scoreNormal() {
        if (interactingPlayerDescriptor.getDrawnFCard() == null) {
            msg = ", the player haven't drawn a card. Please draw a card by writing the command \'draw\'";
            return new PostStatus(Commands.roll
                    + msg, true);
        }
        int score = scoreEvaluator.getScore(interactingPlayerDescriptor.getDrawnFCard(), diceSet);

        msg = "the player got a score of " + score + " points from this dice set";
        boolean success = true;

        RuleResult have3Skulls = scoreEvaluator.rulePlayerDieIf3Skulls(diceSet, interactingPlayerDescriptor.getDrawnFCard());
        RuleResult have4SkullsOnFirstRoll = scoreEvaluator.ruleGoToSkullIslandIf4Skulls(diceSet, interactingPlayerDescriptor.getDrawnFCard());
        RuleResult useSorceress = scoreEvaluator.ruleRollOneSkull(diceSet,
                interactingPlayerDescriptor.getDrawnFCard());

        if (useSorceress.isPass() && have3Skulls.isPass()) {
            boolean canUseSorceressCard;
            if (RIGID) {
                canUseSorceressCard = useTheSorceressCardInput("parrot");
            } else {
                canUseSorceressCard = useTheSorceressCard();
            }
            if (canUseSorceressCard) {
                score = scoreEvaluator.getScore(interactingPlayerDescriptor.getDrawnFCard(), diceSet);
                msg = "player got 3 skull but reroll one skull since got one Sorceress card";
            }
        }

        PostStatus toReturn = new PostStatus(Commands.outcome
                + " " + interactingPlayerDescriptor.getDrawnFCard().getFigure()
                + ", " + diceSet.toString()
                + ", " + score
                + ", " + msg,
                success);
        return toReturn;
    }

}
