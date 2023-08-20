package com.unasat.memorygame.app;

import com.unasat.memorygame.api.DeckOfCards;
import com.unasat.memorygame.config.Config;
import com.unasat.memorygame.services.DeckApiService;
import com.unasat.memorygame.services.DeckDatasource;

import java.util.*;


public class Board {
    private final DeckDatasource deckDatasource = new DeckOfCards();
    private final DeckApiService deckApiService = new DeckApiService(deckDatasource);

    int columns;

    ArrayList<Card> cards = new ArrayList<>();

    Map<Integer, Card> cardPositions = new HashMap<>();

    public Board(String[] cards, int columns) {
        this.columns = columns;

        // Create a pair for every card
        for (String card : cards) {
            this.cards.add(new Card(card));
            this.cards.add(new Card(card));
        }

        // Create card positions
        for (int p = 0; p < this.cards.size(); p++) {
            cardPositions.put(p, this.cards.get(p));
        }
    }

    /*
     * Shuffle the cards on the board
     */
    public void shuffle() {
        cardPositions.clear();

        if (Config.USE_DECK_OF_CARDS_API) {
            try {
                cardPositions = deckApiService.createShuffledDeck();
            } catch (Exception e) {
                throw new RuntimeException("An error occurred while creating a shuffled deck.");
            }
        } else {
            Random random = new Random();
            ArrayList<Card> cardsToProcess = new ArrayList<>(this.cards);

            for (int i = 0; i < this.cards.size(); i++) {
                Card randomCard = cardsToProcess.get(random.nextInt(cardsToProcess.size()));
                randomCard.setDisplayName(randomCard.getValue());
                randomCard.setPosition(i + 1);

                cardPositions.put(i + 1, randomCard);
                cardsToProcess.remove(randomCard);
            }
        }
    }

    /*
     * Get a String representation of the board with cards
     */
    public String getBoard() {
        StringBuilder boardString = new StringBuilder();
        int columnsProcessed = 0;

        int cardNumber = 1;
        String displayedCardValue;

        for (Card card : this.cardPositions.values()) {
            if (columnsProcessed == this.columns) {
                boardString.append("\n");
                columnsProcessed = 0;
            }

            if (card.isOpened()) {
                displayedCardValue = String.format("%2s", card.getDisplayName());
            } else {
                displayedCardValue = String.format("%2d", cardNumber);
            }

            boardString
                    .append(" | ")
                    .append(displayedCardValue)
                    .append(" | ");

            columnsProcessed++;
            cardNumber++;
        }

        return boardString.toString();
    }

    /*
     * Checks whether a specific card was already opened
     */
    public boolean isCardAlreadyOpened(int position) {
        return this.cardPositions.get(position).isOpened();
    }

    /*
     * Opens a card at a certain position on the board
     */
    public Card openCard(int position) {
        this.cardPositions.get(position).setOpened(true);
        return this.cardPositions.get(position);
    }

    /*
     * Closes a card at a specific position on the board
     */
    public void closeCard(int position) {
        this.cardPositions.get(position).setOpened(false);
    }

    /*
     * Check whether all cards are open
     */
    public boolean allCardsOpen() {
        for (Card card : this.cardPositions.values()) {
            if (!card.isOpened()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return getBoard();
    }
}
