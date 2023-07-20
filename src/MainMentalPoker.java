import algorithms.mental_poker.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MainMentalPoker {
    public static void main(String[] args) {
        int numPlayers = 5;
        List<BigInteger> uiDeck, buyIn;
        EncryptionService encryptionService = new EncryptionServiceImpl();
        Cards cards = new Cards(encryptionService);
        cards.initDeck();

        Player[] players = new Player[numPlayers];
        Croupier croupier = new Croupier(cards);

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(encryptionService, String.valueOf(i+1), cards);
        }

        List<BigInteger> uDeck = cards.deck;

        for (int i = 0; i < numPlayers; i++) {
            uDeck = players[i].encryptCards(uDeck);
        }

        for (int i = 0; i < numPlayers; i++) {
            //Лучше использовать разные переменные и давать им конкретные названия, чтобы было понятно для чего эти переменные и что там внутри
            List<BigInteger> chosenCards = croupier.chooseCard(uDeck, 2);
            List<BigInteger> decryptedCards = null;
            for (int j = (i+1)%numPlayers; j != i; j = (j+1)%numPlayers) {
                decryptedCards = players[j].decryptCards(chosenCards);
            }
            players[i].seeCards(players[i].decryptCards(decryptedCards));
        }

        buyIn = croupier.chooseCard(uDeck, 5);
        for (int i = 0; i < numPlayers; i++) {
            buyIn =  players[i].decryptCards(buyIn);
        }
        croupier.seeBuyIN(buyIn);
    }
}
