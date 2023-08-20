package com.unasat.memorygame.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unasat.memorygame.app.Card;
import com.unasat.memorygame.services.DeckDatasource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DeckOfCards implements DeckDatasource {
    private final String BASE_URL = "https://deckofcardsapi.com/api";

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Card> drawCards(String deckId, int count) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/deck/" + deckId + "/draw/?count=" + count))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode responseData = objectMapper.readTree(response.body());

        JsonNode drawnCards = responseData.get("cards");

        List<Card> cards = new ArrayList<>();

        for (JsonNode card : drawnCards) {
            String cardCode = card.get("code").asText();
            String cardValue = card.get("value").asText();

            Card newCard = new Card(cardValue);
            newCard.setDisplayName(cardCode);

            cards.add(newCard);
        }

        return cards;
    }

    @Override
    public String shuffleDeck(String ...cards) throws Exception {
        String cardsQuery = String.join(",", cards);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/deck/new/shuffle/?cards=" + cardsQuery))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode responseData = objectMapper.readTree(response.body());

        return responseData.get("deck_id").asText();
    }
}
