package labs.lab2;

import java.util.*;

public class Deck {
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
            System.out.printf("%-5s, ", cards.get(i).toString());
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }
}