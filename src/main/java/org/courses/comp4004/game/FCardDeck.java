package org.courses.comp4004.game;

import java.util.ArrayList;
import java.util.List;

public class FCardDeck {

    private List<FCard> deck = new ArrayList<>();

    public FCardDeck() {
        deck.addAll(sameFCardSet(4, "Chest", 0)); //4xChest
        deck.addAll(sameFCardSet(4, "Sorceress", 0)); //, 4xSorceress
        deck.addAll(sameFCardSet(4, "Captain", 0)); //, 4xCaptain
        deck.addAll(sameFCardSet(4, "Monkey&Parrot", 0)); //, 4xMonkey&Parrot
        deck.addAll(sameFCardSet(4, "Diamond", 0)); //, 4xDiamond
        deck.addAll(sameFCardSet(4, "Coin", 0)); //, 4xCoin (Doublon),
        deck.addAll(sameFCardSet(2, "2skulls", 0)); //2x2skulls
        deck.addAll(sameFCardSet(3, "1skull", 0)); //, 3x1skull
        deck.addAll(sameFCardSet(2, "2swords", 300)); //, 2x2swords(300 bonus)
        deck.addAll(sameFCardSet(2, "3swords", 500)); //, 2x3swords(500 bonus)
        deck.addAll(sameFCardSet(2, "4swords", 1000)); //, 2x4swords(1000 bonus)
    }

    public List<FCard> getDeck() {
        return deck;
    }

    public List<FCard> sameFCardSet(int number, String figure, int bonus){
        List<FCard> sameFCards = new ArrayList<>();

        for(int i=0; i<number; i++){
            sameFCards.add(new FCard(figure, bonus));
        }

        return sameFCards;
    }
}
