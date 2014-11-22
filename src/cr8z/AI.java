package cr8z;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Vector;

/**
 * Created by Kevin on 11/6/2014.
 */
public class AI {


    ImageIcon back;
    Vector<Position> cardPosition = new Vector<Position>();
    Vector<Card> hand = new Vector<Card>();
    Suit suits[] = {Suit.Clubs,Suit.Diamonds,Suit.Hearts,Suit.Spades};
    int w = 60,
            h = 80;

    AI(Vector<Card> hand) {
        this.hand = hand;
        mkCardPostitions();

        try {
            back = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/cr8z/img/back.jpg")));
            back = ScaleIMageIcon.scale(back, w, h);
        }catch (Exception r){
        }

        showHand();

    }//..

    public void takeTurn(){
        for (Card c:hand){
            if (c.suit == Cr8z.suitToPlay){
                Cr8z.discard(hand,c);
                Cr8z.isPlayersTurn = true;
                return;
            }
            if(c.face == Cr8z.faceToPlay){
                Cr8z.discard(hand,c);
                Cr8z.isPlayersTurn = true;
                return;
            }
            if(c.face == Face.Eight){
                Cr8z.discard(hand,c);
                Cr8z.suitToPlay = selectRansSuit();
                Cr8z.isPlayersTurn = true;
                return;
            }
        }
        drawCard();
        if(!Cr8z.isPlayersTurn)
            takeTurn();
    }//..

    protected Suit selectRansSuit(){
        Suit suit = suits[(int)(Math.random()*suits.length)];
        JOptionPane.showMessageDialog(null,"AI chose "+suit);
        return suit;
    }//..

    protected void mkCardPostitions(){

        int h = 10;
        for (int y= 2;y<2+(h*4);y+=h) {
            for (int x = 0; x < 8; x++) {
                cardPosition.add(new Position((x * 70) + 10, y));
            }
        }

    }//..

    public void showHand(){
        for (int i=0;i<hand.size();i++){
            hand.get(i).graphic.setIcon(back);
            hand.get(i).p = cardPosition.get(i);
        }
    }//..

    public void drawCard(){
        if(Cr8z.deck.size()>0) {
            int rand = (int) (Math.random() * 4) + 1;
            PlaySound.play(getClass(), "/cr8z/snd/cardShove" + rand + ".wav");
            Card card = Cr8z.deck.firstElement();
            card.graphic.setIcon(back);
            card.p = getNextEmptyPosition();
            hand.add(card);
            Cr8z.deck.remove(card);
        }else {
            JOptionPane.showMessageDialog(null,"AI cannot go, It\'s your turn!");
            Cr8z.deckCover.back.setIcon(Cr8z.deckCover.gone);
            Cr8z.isPlayersTurn = true;
        }

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

}// end Class AI
