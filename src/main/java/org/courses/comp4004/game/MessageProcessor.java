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
            }
        } else {
            msg = "unknown command";
            toReturn = new PostStatus(msg, true);
        }
        return toReturn;
    }
}
