import com.unasat.memorygame.app.Card;
import com.unasat.memorygame.services.DeckApiService;
import com.unasat.memorygame.services.DeckDatasource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestDeckApiService {
    DeckApiService deckApiService = new DeckApiService(new DeckDatasource() {
        @Override
        public List<Card> drawCards(String deckId, int count) {
            return new ArrayList<>(Arrays.asList(
                    new Card("AH"), new Card("2H"), new Card("3H"), new Card("4H"),
                    new Card("5H"), new Card("6H"), new Card("7H"), new Card("8H"),
                    new Card("9H"), new Card("0H"), new Card("AD"), new Card("2D"),
                    new Card("3D"), new Card("4D"), new Card("5D"), new Card("6D"),
                    new Card("7D"), new Card("8D"), new Card("9D"), new Card("0D")
            ));
        }

        @Override
        public String shuffleDeck(String... cards) {
            return "ustrgwx15916";
        }
    });

    @Test
    @DisplayName("Board should have 20 cards")
    public void boardShouldHave20Cards() throws Exception {
        Map<Integer, Card> cardPositions = deckApiService.createShuffledDeck();
        Assertions.assertEquals(20, cardPositions.size());
    }

    @Test
    @DisplayName("Cards should be on the correct position")
    public void cardsShouldBeOnCorrectPosition() throws Exception {
        Map<Integer, Card> cardPositions = deckApiService.createShuffledDeck();
        Assertions.assertEquals("AH", cardPositions.get(1).getValue());
        Assertions.assertEquals("5H", cardPositions.get(5).getValue());
        Assertions.assertEquals("0H", cardPositions.get(10).getValue());
        Assertions.assertEquals("0D", cardPositions.get(20).getValue());
    }
}
