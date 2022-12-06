package algorithms.mental_poker;
import algorithms.cryptosys_public_key.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    private String[] cards;
    public String name;

    private static BigInteger P;
    private BigInteger C;
    private BigInteger D;

    public Cards chargedDeck;

    public static void generateBigPrimeP() {
        do {
            P = getRandomBigInteger(30);
        } while(!P.isProbablePrime(100));
    }

    public Player( String name, Cards deck) {
        cards = new String[2];
        this.name = name;
        chargedDeck = deck;
        long[] EuclidResult;
        BigInteger temp_P = P.subtract(BigInteger.valueOf(1));
        do {
            do {
                C = getRandomBigInteger(30);
                EuclidResult = Euclid.calculate(C.longValue(), temp_P.longValue());
            } while (EuclidResult[0] != 1);
            D = new BigInteger((String.valueOf(EuclidResult[2] + temp_P.longValue())));
        } while(!C.multiply(D).mod(BigInteger.valueOf(temp_P.longValue())).equals(BigInteger.ONE));
    }

    public ArrayList<BigInteger> encryptCards(ArrayList<BigInteger> cards) {
        ArrayList<BigInteger> uDeck = new ArrayList<>(cards.size());
        for (BigInteger card : cards) {
            uDeck.add(card.modPow(getC(), P));
        }
        Collections.shuffle(uDeck);
        return uDeck;
    }

    public ArrayList<BigInteger> decryptCards(ArrayList<BigInteger> cards) {
        ArrayList<BigInteger> uDeck = new ArrayList<>(cards.size());
        for (BigInteger card : cards) {
            uDeck.add(card.modPow(getD(), P));
        }
        Collections.shuffle(uDeck);
        return uDeck;
    }

    public void seeCards(ArrayList<BigInteger> cards) {
        System.out.println("\tPlayer "+name+": key = "+getD());
        for (int i = 0; i < cards.size(); i++) {
            this.cards[i] = chargedDeck.deckInterpreter(chargedDeck.deck, cards.get(i));
            System.out.print(" < "+this.cards[i]+" >");
        }
        System.out.println("\n");
    }


    public static BigInteger getRandomBigInteger(int numBits) {
        BigInteger number = new BigInteger(numBits, new Random());
        return number.setBit(0);
    }

    public BigInteger getC() {
        return C;
    }
    public BigInteger getD() {
        return D;
    }
    public static BigInteger getP() {
        return P;
    }
    public String[] getCards() {
        return cards;
    }
    public void setCards(String[] cards) {
        this.cards = cards;
    }
}
