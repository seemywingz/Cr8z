package cr8z;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

/**
 * Created by Kevin on 11/5/2014.
 */
public class Cr8z extends JPanel {

    static JPanel panel;
    static Player player;
    static AI ai;

    BufferedImage allCardImages;

    static CardBack deckCover;
    static Vector<Card> deck  = new Vector<Card>();
    final static int
            deckX = 160,
            deckY = 130,
            discX = 260,
            discY = 130;

    static Suit suitToPlay;
    static Face faceToPlay;
    static Card topOfDiscardPile;
    static boolean isPlayersTurn = true,
            pause = false;


    Cr8z() {
        panel = this;
        setLayout(null);
        mkDeck();
        shuffleDeck();
        dealCards();
        createBG();
        PlaySound.play(getClass(),"/cr8z/snd/deal.wav");

        startThread();
    }//..

    protected void mkDeck(){
        try {

            deckCover = new CardBack(new Position(deckX,deckY));
            deckCover.isDeckCover = true;

            allCardImages = ImageIO.read(getClass().getResourceAsStream("/cr8z/img/cards.png"));

            for (Suit s : Suit.values() ){
                for (Face f: Face.values()){
                    deck.add(new Card(allCardImages,s,f,new Position(deckX,deckY)));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//..

    protected void shuffleDeck(){
        for(int i = 0;i<100;i++) {
            int w = (int) (Math.random() * deck.size());
            int z = (int) (Math.random() * deck.size());
            Collections.swap(deck, w, z);
        }
    }//..

    protected void dealCards(){

        Vector<Card> hand = new Vector<Card>();

        for (int i=0;i<8;i++){
            hand.add(deck.get(i));
            deck.removeElementAt(i);
        }
        player = new Player(hand);

        hand = new Vector<Card>();

        for (int i=0;i<8;i++){
            hand.add(deck.get(i));
            deck.removeElementAt(i);
        }
        ai = new AI(hand);

        discard(deck,deck.get((int)(Math.random()*deck.size())));

    }//..

    public static void discard(Vector<Card> pile,Card card){
        topOfDiscardPile = card;
        topOfDiscardPile.isInPlayerHand = false;
        topOfDiscardPile.graphic.setIcon(topOfDiscardPile.img);
        topOfDiscardPile.p = new Position(discX,discY);
        suitToPlay = topOfDiscardPile.suit;
        faceToPlay = topOfDiscardPile.face;
        pile.remove(card);
        int rand = (int)(Math.random()*8)+1;
        PlaySound.play(panel.getClass(),"/cr8z/snd/cardSlide"+rand+".wav");
    }//..

    public void createBG(){
        try {
            ImageIcon img = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/cr8z/img/bg.jpg")));
            img = ScaleIMageIcon.scale(img, 600, 500);
            JLabel bg = new JLabel(img);
            bg.setBounds(0, 0, 600, 500);
            add(bg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//..

    protected void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{

                        if(!isPlayersTurn && !pause){
                            ai.takeTurn();
                        }
                        if(ai.hand.size() == 0){
                            JOptionPane.showMessageDialog(null,"Awww the AI Beat you this Time!!!\n" +
                                    "Try again another time :)");
                            System.exit(0);
                        }
                        if(player.hand.size() == 0){
                            JOptionPane.showMessageDialog(null,"WOOT!!!!\nYou beat the AI!!!");
                            System.exit(0);
                        }
                        setComponentZOrder(topOfDiscardPile.graphic, 0);
                        repaint();
                        Thread.sleep(20);
                    }catch (Exception e){
                    }
                }
            }
        }).start();
    }//..

}// end Class Cr8zPanel
