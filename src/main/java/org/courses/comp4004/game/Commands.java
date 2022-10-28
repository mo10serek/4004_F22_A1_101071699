package org.courses.comp4004.game;

public class Commands {
    public final static String modeInteracting  = "mode.interacting"; //  <null>
    public final static String modeServerBroadcasting = "mode.server.broadcasting"; //  <null>
    public final static String inform           = "inform"; //  <msg> (Information send to player)
    public final static String draw             = "draw"; //  <null> (player requests drawing the card form Fortune Card deck)
    public final static String roll             = "roll"; //  <null> (roll the whole DiceSet)
    public final static String outcome          = "outcome"; //  <FCard>,<DiceSet>,<Player1Score>,<Player2Score>,<Player3Score>(just display updated top fortune card, dices set and scores  )
    public final static String help             = "help"; // <null> (print all the commands to the player)
    public final static String takeCard         = "take.card"; // <card> (find a card that can be used for testing)


}
