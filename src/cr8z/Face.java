package cr8z;

/**
 * Created by Kevin on 11/5/2014.
 */
public enum Face {
    Ace(1),
    Two(74),
    Three(147),
    Four(220),
    Five(293),
    Six(366),
    Seven(439),
    Eight(512),
    Nine(585),
    Ten(658),
    Jack(731),
    Queen(804),
    King(877);

    private int val;

    Face(int val) {
        this.val = val;
    }//..

    public int getVal() {
        return val;
    }//..

}// end Class Face
