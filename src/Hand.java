import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;
    private Deck deck;

    public Hand(Deck deck) {
        this.deck = deck;
        this.cards = new ArrayList<Card>();
    }

    // 获取手牌张数
    public int getLength() {
        return cards.size();
    }

    // 根据下标获取一张手牌
    public Card get(int index) {
        return cards.get(index);
    }
    
    // 从牌堆顶部拿取一张牌
    public void getOne() {
        cards.add(deck.shift());
    }


    // 获取当前手牌总值
    public int getValue() {
        int length = cards.size();
        int result = 0;
        List<Boolean> numA = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            switch (cards.get(i).getValue()) {
                case 1:
                    numA.add(true);
                    break;
                case 11:
                case 12:
                case 13:
                    result += 10;
                    break;
                default:
                    result += cards.get(i).getValue();
            }
        }
        for (int i = 0; i < numA.size(); i++) {
            if (result + 11 <= 21) {
                result += 11;
            } else if (result + 1 <= 21) {
                result += 1;
            } else {
                if (i != 0) {
                    result = result - 10 + 1;
                } else {
                    result += 1;
                }
            }
        }
        return result;
    }

    public boolean isBlackJack() {
        return getLength() == 2 && getValue() == 21;
    }

    public boolean mayBeBlackJack() {
        return cards.get(0).getValue() == 1;
    }
}
