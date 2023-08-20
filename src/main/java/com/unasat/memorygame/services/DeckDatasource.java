package com.unasat.memorygame.services;

import com.unasat.memorygame.app.Card;

import java.util.List;

public interface DeckDatasource {
    List<Card> drawCards(String deckId, int count) throws Exception;
    String shuffleDeck(String ...cards) throws Exception;
}
