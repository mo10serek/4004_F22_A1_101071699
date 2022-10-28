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
    public final static String setDice          = "set.dice"; // <Dice1>,<Dice2>,...,<DiceN> (pick which faces to use for testing)
    public final static String useSorceress     = "use.sorceress"; //  <null> (player use sorceress to reroll one skull dice dice
    public final static String holdChest        = "hold"; // <Dice1>,<Dice2>,...,<DiceN>   (player request which dice to hold)
    public final static String takeOutChest     = "takeOut"; // <Dice1>,<Dice2>,...,<DiceN>   (player request which dice to take out)

}
