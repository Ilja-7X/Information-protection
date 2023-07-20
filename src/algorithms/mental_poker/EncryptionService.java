package algorithms.mental_poker;

import java.math.BigInteger;
import java.util.List;

/**
 * Чтобы вынести всю логику с шифрованием лучше создать отдельный интерфейс (Благодаря интерфейсу в других местах будет завязка на
 * интерфейс и можно будет подставить любую реализацию этого сервиса - т.е. любой алгоритм шифрования
 *
 * Ну и лучше отделять какие-то разные логики, у тебя есть логика игры в карты и есть логика связанная с шифрованием, все что связано с шифрованием лучше вынести в отдельный класс
 * Не надо будет распихивать по классам эти константы в виде Р
 */

public interface EncryptionService {
    List<BigInteger> generateCards(int numberOfCards);
    List<BigInteger> encryptCards(List<BigInteger> cards);
    List<BigInteger> decryptCards(List<BigInteger> cards);
    String logInfo();
}
