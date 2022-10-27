package org.courses.comp4004.game;

public class PlayerDescriptor {private PlayerStreams playerStreams;
    private DiceSet diceSet;
    private FCard drawnFCard;
    ScorePad scorePad;

    public PlayerDescriptor() {
    }

    public PlayerDescriptor(PlayerStreams playerStreams, ScorePad scorePad) {
        this.playerStreams = playerStreams;
        this.scorePad = scorePad;
    }

    public PlayerStreams getPlayerStreams() {
        return playerStreams;
    }

    public void setPlayerStreams(PlayerStreams playerStreams) {
        this.playerStreams = playerStreams;
    }

    public FCard getDrawnFCard() {
        return drawnFCard;
    }

    public void setDrawnFCard(FCard drawnFCard) {
        this.drawnFCard = drawnFCard;
    }

    public ScorePad getScorePad() {
        return scorePad;
    }

    public void setScorePad(ScorePad scorePad) {
        this.scorePad = scorePad;
    }
    public DiceSet getDiceSet() { return diceSet; }

    public void setDiceSet(DiceSet diceSet) { this.diceSet = diceSet; }
    @Override
    public String toString() {
        return "PlayerDescriptor{" +
                "playerStreams=" + playerStreams +
                ", scorePad=" + scorePad +
                '}';
    }
}
