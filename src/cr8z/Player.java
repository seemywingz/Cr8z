package cr8z;

import java.util.Vector;

/**
 * Created by Kevin on 11/5/2014.
 */
public class Player {


    Vector<Position> cardPosition = new Vector<Position>();
    Vector<Card> hand = new Vector<Card>();

    Player(Vector<Card> hand) {
        this.hand = hand;
        mkCardPostitions();
        showHand();
    }//..

    protected void mkCardPostitions(){

        int h = 85;
        for (int y= 250;y<250+(h*4);y+=h) {
            for (int x = 0; x < 8; x++) {
                cardPosition.add(new Position((x * 70) + 10, y));
            }
        }

    }//..

    public void showHand(){
        for (int i=0;i<hand.size();i++){
            hand.get(i).p = cardPosition.get(i);
            hand.get(i).isInPlayerHand = true;
        }
    }//..

    public void discard(Card card){
        Cr8z.discard(hand,card);
    }//..

    public void takeCard(Card card){
        int rand = (int)(Math.random()*4)+1;
        PlaySound.play(getClass(),"/cr8z/snd/cardShove"+rand+".wav");
        card.isInPlayerHand = true;
        card.p = getNextEmptyPosition();
        hand.add(card);

    }//..

    protected Position getNextEmptyPosition(){
        Vector<Position> emptyPositions = new Vector<Position>();
        for(Position p: cardPosition){
            emptyPositions.add(p);
        }
        for (Card c: hand ){
            emptyPositions.remove(c.p);
        }
        return emptyPositions.get(0);
    }//..

}// end Class PLayer
