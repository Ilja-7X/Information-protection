package algorithms.mental_poker;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cards {
    public static int NUM_OF_CARDS = 52;
    public static int NUM_OF_SUITS = 4;
    public static int NUM_OF_VALUES = 13;

//    public ArrayList<String> suitName = new ArrayList<>(NUM_OF_SUITS);
//    public ArrayList<String> valuesName = new ArrayList<>(NUM_OF_VALUES);
    public List<Card> cards;

    public List<BigInteger> deck;
    private final EncryptionService encryptionService;

    public Cards(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
//        suitName.add("Hearts");
//        suitName.add("Diamonds");
//        suitName.add("Spades");
//        suitName.add("Clubs");
//
//        valuesName.add("two");
//        valuesName.add("three");
//        valuesName.add("four");
//        valuesName.add("five");
//        valuesName.add("six");
//        valuesName.add("seven");
//        valuesName.add("eight");
//        valuesName.add("nine");
//        valuesName.add("ten");
//        valuesName.add("jack");
//        valuesName.add("queen");
//        valuesName.add("king");
//        valuesName.add("ace");
        //Вместо строковых констант, лучше использовать enum, если есть какой-то заданный набор констант и они пренадлежат к чему-то одному.
        //Здесь это масть и номер карт - два енама
        //В классе Bulletin это варианты ответов
        //Так же стоило сделать отдельный класс "Карта" - где были бы переменные "масть" и "номер"


        //Инициализация набора карт, берем стрим мастей, дальше берем стрим номеров и превращаем их карты
        //flatMap, чтобы развернуть несколько стримов в один, т.к. на каждый элемент из стрима мастей открывается свой стрим номеров
        cards = Stream.of(Card.Suit.values())
                .flatMap(s -> Stream.of(Card.Value.values())
                        .map(v -> new Card(s, v))
                ).collect(Collectors.toList());

    }

    public void initDeck() {
        deck = encryptionService.generateCards(NUM_OF_CARDS);
    }

    public String deckInterpreter(List<BigInteger> cardDeck, BigInteger cardValue) {
        int suit, value;
        String cardName;
        for (int i = 0; i < NUM_OF_CARDS; i++) {
            if (cardDeck.get(i).equals(cardValue)) {
                suit = i / NUM_OF_VALUES;
                value = i % NUM_OF_VALUES;
                cardName = Card.Suit.values()[suit] + " " + Card.Value.values()[value];
                return cardName;
            }
        }
        return null;
    }
}
