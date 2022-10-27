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
        } else {
            msg = "unknown command";
            toReturn = new PostStatus(msg, true);
        }
        return toReturn;
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

        PostStatus toReturn = new PostStatus(Commands.outcome
                + " " + interactingPlayerDescriptor.getDrawnFCard().getFigure()
                + ", " + diceSet.toString()
                + ", " + score
                + ", " + msg,
                success);
        return toReturn;
    }

}
