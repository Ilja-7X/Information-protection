package algorithms.mental_poker;

import algorithms.cryptosys_public_key.Euclid;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EncryptionServiceImpl implements EncryptionService {
    private BigInteger P;
    private BigInteger C;
    private BigInteger D;

    public EncryptionServiceImpl() {
        do {
            P = getRandomBigInteger(30);
        } while(!P.isProbablePrime(100));

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

    @Override
    public List<BigInteger> generateCards(int numberOfCards) {
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());

        return IntStream.range(0, numberOfCards + 1)
                .mapToObj(i -> new BigInteger(String.valueOf(srand.nextInt(P.intValue()-2)+1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<BigInteger> encryptCards(List<BigInteger> cards) {
        // Лучше пользоваться stream api, Код говорит что мы делаем с коллекцией, map - преобразование, filter - отфильтровываем элементы
        var uDeck = cards.stream()
                .map(card -> card.modPow(C, P))
                .collect(Collectors.toList());
        Collections.shuffle(uDeck);
        return uDeck;
    }

    @Override
    public List<BigInteger> decryptCards(List<BigInteger> cards) {
        ArrayList<BigInteger> uDeck = new ArrayList<>(cards.size());
        for (BigInteger card : cards) {
            uDeck.add(card.modPow(D, P));
        }
        Collections.shuffle(uDeck);
        return uDeck;
    }

    @Override
    public String logInfo() {
        return D.toString();
    }

    public static BigInteger getRandomBigInteger(int numBits) {
        BigInteger number = new BigInteger(numBits, new Random());
        return number.setBit(0);
    }
}
