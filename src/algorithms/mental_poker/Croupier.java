package algorithms.mental_poker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.security.SecureRandom;
import java.util.List;

public class Croupier {
    public Cards chargedDeck;
    private String[] buyIn;

    public Croupier(Cards deck) {
        buyIn = new String[5];
        chargedDeck = deck;
    }

    public List<BigInteger> chooseCard (List<BigInteger> cards, int numOfCards) {
        ArrayList<BigInteger> selectedCards = new ArrayList<>(numOfCards);
        int randomInteger;
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < numOfCards; i++) {
            randomInteger = srand.nextInt(cards.size());
            selectedCards.add(cards.get(randomInteger));
            cards.remove(randomInteger);
        }
        return selectedCards;
    }
    public void seeBuyIN(List<BigInteger> cards) {
        System.out.println("\t\tTable Deck ");
        for (int i = 0; i < cards.size(); i++) {
            this.buyIn[i] = chargedDeck.deckInterpreter(chargedDeck.deck, cards.get(i));
            System.out.println(" ["+this.buyIn[i]+"]");
        }
        System.out.println("\n");
    }
}
