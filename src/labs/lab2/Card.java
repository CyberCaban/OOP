package labs.lab2;

enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES
}

enum Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

public class Card {
Suit suit;
        Rank rank;
    
        Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }
    
        public String toString() {
            return String.format("%-5s of %-8s", rank, suit);
        }
}
