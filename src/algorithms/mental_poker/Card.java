package algorithms.mental_poker;


public class Card {
    //Поля у модели делаются приватными с гетерами и сетерами
    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValues() {
        return value;
    }

    public enum Suit {
        HEARTS(1), DIAMONDS(2), SPADES(3), CLUBS(4);
        Suit(int num) {
            this.num=num;
        }
        int num;

        //Тут лучше пользоваться ломбоком, чтоб геттеры генерировались сами
        public int getNum() {
            return num;
        }
    }
    public enum Value {
        TWO(1), THREE(2), FOUR(3), FIVE(4), SIX(5), SEVEN(6), EIGHT(7), NINE(8), TEN(9), JACK(10), QUEEN(11), KING(12), ACE(13);
        Value(int num) {
            this.num=num;
        }
        int num;

        public int getNum() {
            return num;
        }
    }
}
