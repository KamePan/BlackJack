import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private int currentIndex;

    public Deck() {
        currentIndex = 0;
        cards = Arrays.asList(
            // 黑桃 ♠
            new Card(Suit.SPADE, 1), new Card(Suit.SPADE, 2),
            new Card(Suit.SPADE, 3), new Card(Suit.SPADE, 4),
            new Card(Suit.SPADE, 5), new Card(Suit.SPADE, 6),
            new Card(Suit.SPADE, 7), new Card(Suit.SPADE, 8),
            new Card(Suit.SPADE, 9), new Card(Suit.SPADE, 10),
            new Card(Suit.SPADE, 11), new Card(Suit.SPADE, 12),
            new Card(Suit.SPADE, 13),

            // 红心 ♥
            new Card(Suit.HEART, 1), new Card(Suit.HEART, 2),
            new Card(Suit.HEART, 3), new Card(Suit.HEART, 4),
            new Card(Suit.HEART, 5), new Card(Suit.HEART, 6),
            new Card(Suit.HEART, 7), new Card(Suit.HEART, 8),
            new Card(Suit.HEART, 9), new Card(Suit.HEART, 10),
            new Card(Suit.HEART, 11), new Card(Suit.HEART, 12),
            new Card(Suit.HEART, 13),

            // 梅花 ♣
            new Card(Suit.CLUB, 1), new Card(Suit.CLUB, 2),
            new Card(Suit.CLUB, 3), new Card(Suit.CLUB, 4),
            new Card(Suit.CLUB, 5), new Card(Suit.CLUB, 6),
            new Card(Suit.CLUB, 7), new Card(Suit.CLUB, 8),
            new Card(Suit.CLUB, 9), new Card(Suit.CLUB, 10),
            new Card(Suit.CLUB, 11), new Card(Suit.CLUB, 12),
            new Card(Suit.CLUB, 13),

            // 方块 ♦
            new Card(Suit.DIAMOND, 1), new Card(Suit.DIAMOND, 2),
            new Card(Suit.DIAMOND, 3), new Card(Suit.DIAMOND, 4),
            new Card(Suit.DIAMOND, 5), new Card(Suit.DIAMOND, 6),
            new Card(Suit.DIAMOND, 7), new Card(Suit.DIAMOND, 8),
            new Card(Suit.DIAMOND, 9), new Card(Suit.DIAMOND, 10),
            new Card(Suit.DIAMOND, 11), new Card(Suit.DIAMOND, 12),
            new Card(Suit.DIAMOND, 13)
        );

        Collections.shuffle(cards);
    }

    // 根据currentIndex返回一张牌
    public Card shift() {
        if (currentIndex == 52) {
            currentIndex = 0;
            Collections.shuffle(cards);
        }
        return cards.get(currentIndex++);
    }
}
