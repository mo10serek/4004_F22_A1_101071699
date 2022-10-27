package org.courses.comp4004.game;

import java.util.List;

import static java.util.Objects.isNull;

public class MessageProcessor {
    private FCardDeck fCardDeck = null;
    private DiceSet diceSet = null;
    private ScoreEvaluator scoreEvaluator = null;
    private LineParser lineParser = null;
    private List<PlayerDescriptor> playerDescriptorList  = null;

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

            }
        } else {
            msg = "unknown command";
            toReturn = new PostStatus(msg, true);
        }
        return toReturn;
    }
}