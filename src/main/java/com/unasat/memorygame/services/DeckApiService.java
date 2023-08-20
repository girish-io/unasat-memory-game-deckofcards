package com.unasat.memorygame.services;

import com.unasat.memorygame.app.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckApiService {
    private final DeckDatasource deckDatasource;

    public DeckApiService(DeckDatasource deckDatasource) {
        this.deckDatasource = deckDatasource;
    }

    public Map<Integer, Card> createShuffledDeck() throws Exception {
        Map<Integer, Card> cardPositions = new HashMap<>();

        String deckId = deckDatasource.shuffleDeck(
                "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "0H",
                "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "0D");

        List<Card> cards = deckDatasource.drawCards(deckId, 20);

        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setPosition(i + 1);
            cardPositions.put(i + 1, cards.get(i));
        }

        return cardPositions;
    }
}
