package algorithms.mental_poker;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Cards {
    public static int NUM_OF_CARDS = 52;
    public static int NUM_OF_SUITS = 4;
    public static int NUM_OF_VALUES = 13;

    public ArrayList<String> suitName = new ArrayList<>(NUM_OF_SUITS);
    public ArrayList<String> valuesName = new ArrayList<>(NUM_OF_VALUES);

    public ArrayList<BigInteger> deck = new ArrayList<BigInteger>(NUM_OF_CARDS);

    public Cards() {
        suitName.add("Hearts");
        suitName.add("Diamonds");
        suitName.add("Spades");
        suitName.add("Clubs");

        valuesName.add("two");
        valuesName.add("three");
        valuesName.add("four");
        valuesName.add("five");
        valuesName.add("six");
        valuesName.add("seven");
        valuesName.add("eight");
        valuesName.add("nine");
        valuesName.add("ten");
        valuesName.add("jack");
        valuesName.add("queen");
        valuesName.add("king");
        valuesName.add("ace");
    }

    public void initDeck(BigInteger P) {
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < NUM_OF_CARDS; i++) {
            deck.add(new BigInteger(String.valueOf(srand.nextInt(P.intValue()-2)+1)));
        }
    }

    public String deckInterpreter(ArrayList<BigInteger> cardDeck, BigInteger cardValue) {
        int suit,value;
        String cardName;
        for (int i = 0; i < NUM_OF_CARDS; i++) {
            if (cardDeck.get(i).equals(cardValue)) {
                suit = i / NUM_OF_VALUES;
                value = i % NUM_OF_VALUES;
                cardName = valuesName.get(value)+" "+suitName.get(suit);
                return cardName;
            }
        }
        return null;
    }
}
