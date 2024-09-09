package labs;

import java.util.*;

enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES
}

enum Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

class Card {
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

class Deck {
    List<Card> cards = new ArrayList<>();

    Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public Card dealCard() {
        return cards.remove(getRandom(0, cards.size()));
    }

    public String toString() {
        return cards.toString();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void prettyPrint() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.printf("%-5s, ",cards.get(i).toString());
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }
}