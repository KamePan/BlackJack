public class Card {
    private final Suit suit;
    private final int value;

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public String toString() {
        String displayValue;
        switch (this.value) {
            case 1:
                displayValue = "A";
                break;
            case 11:
                displayValue = "J";
                break;
            case 12:
                displayValue = "Q";
                break;
            case 13:
                displayValue = "K";
                break;
            default:
                displayValue = Integer.toString(this.value);
        }
        return this.suit + " " + displayValue;
    }
}
