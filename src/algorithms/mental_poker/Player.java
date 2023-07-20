package algorithms.mental_poker;

import java.math.BigInteger;
import java.util.List;

public class Player {
    private final EncryptionService encryptionService;
    private String[] cards;
    public String name;

    public Cards chargedDeck;

    public Player(EncryptionService encryptionService, String name, Cards deck) {
        this.encryptionService = encryptionService;
        cards = new String[2];
        this.name = name;
        chargedDeck = deck;
    }

    //Надо использовать интерфейсы, особенно если речь о каких-то коллекциях, не надо завязываться на конкретные реализации
    public List<BigInteger> encryptCards(List<BigInteger> cards) {


        return encryptionService.encryptCards(cards);
    }

    public List<BigInteger> decryptCards(List<BigInteger> cards) {
        return encryptionService.decryptCards(cards);
    }

    public void seeCards(List<BigInteger> cards) {
        System.out.println("\tPlayer "+name+": key = "+encryptionService.logInfo());
        for (int i = 0; i < cards.size(); i++) {
            this.cards[i] = chargedDeck.deckInterpreter(chargedDeck.deck, cards.get(i));
            System.out.print(" ["+this.cards[i]+"]");
        }
        System.out.println("\n");
    }

    public String[] getCards() {
        return cards;
    }
    public void setCards(String[] cards) {
        this.cards = cards;
    }
}
