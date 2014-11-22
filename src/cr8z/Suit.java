package cr8z;

/**
 * Created by Kevin on 11/5/2014.
 */
public enum Suit {
    Clubs(1),
    Spades(99),
    Hearts(197),
    Diamonds(295);

    private int val;

    Suit(int val) {
        this.val = val;
    }//..

    public int getVal() {
        return val;
    }//..

}// end Class Suits
