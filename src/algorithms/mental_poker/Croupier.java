package algorithms.mental_poker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.security.SecureRandom;
public class Croupier {
    public BigInteger P;
    public Cards chargedDeck;
    private String[] buyIn;

    public Croupier(Cards deck, BigInteger P) {
        this.P = P;
        buyIn = new String[5];
        chargedDeck = deck;
    }

    public ArrayList<BigInteger> chooseCard (ArrayList<BigInteger> cards, int numOfCards) {
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
    public void seeBuyIN(ArrayList<BigInteger> cards) {
        System.out.println("\t\tTable Deck ");
        for (int i = 0; i < cards.size(); i++) {
            this.buyIn[i] = chargedDeck.deckInterpreter(chargedDeck.deck, cards.get(i));
            System.out.println(" ["+this.buyIn[i]+"]");
        }
        System.out.println("\n");
    }
}
