package cr8z;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Kevin on 11/5/2014.
 */
public class Card {

    // mechanic
    Card card;
    Suit suit;
    Face face;
    boolean isInPlayerHand = false;

    // graphic
    Position p;
    ImageIcon img,zImg;
    JLabel graphic;
    int w = 60,
        h = 80,
        zw = 62,
        zh = 84;



    Card(BufferedImage cards,Suit suit, Face face,Position p ) {
        card = this;
        this.suit = suit;
        this.face = face;
        this.p = p;
        mkGrapgic(cards);
        mkMouseListener();
        startThread();
    }//..

    protected void mkGrapgic(BufferedImage cards){

        int iw = 72,
            ih = 96;

        zImg = new ImageIcon(cards.getSubimage(face.getVal(),suit.getVal(),iw,ih));
        img = new ImageIcon(cards.getSubimage(face.getVal(),suit.getVal(),iw,ih));
        img = ScaleIMageIcon.scale(img,w,h); zImg = ScaleIMageIcon.scale(zImg,zw,zh);

        graphic = new JLabel(img);
        graphic.setBounds(p.x,p.y,w,h);
        Cr8z.panel.add(graphic);

    }//..

    protected void mkMouseListener(){
        graphic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playLogic();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(isInPlayerHand)
                zoomIn();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(isInPlayerHand)
                zoomOut();
            }
        });
    }//..

    protected void zoomIn(){
        graphic.setIcon(zImg);
        graphic.setBounds(p.x,p.y,zw,zh);

    }//
    protected void zoomOut(){
        int rand = (int)(Math.random()*4)+1;
        PlaySound.play(getClass(),"/cr8z/snd/cardPlace"+rand+".wav");
        graphic.setIcon(img);
        graphic.setBounds(p.x,p.y,w,h);

    }//

    protected void playLogic(){
        if(isInPlayerHand && Cr8z.isPlayersTurn) {
            if(face == Face.Eight){
                Cr8z.pause = true;
                Cr8z.player.discard(card);
                selectSuitToPlay();
            }else {
                if(suit == Cr8z.suitToPlay){
                    Cr8z.player.discard(card);
                    Cr8z.isPlayersTurn = false;
                }else if(face == Cr8z.faceToPlay){
                    Cr8z.player.discard(card);
                    Cr8z.isPlayersTurn = false;
                }else{
                    if(Cr8z.faceToPlay == Face.Eight){
                        JOptionPane.showMessageDialog(null, "Cannot Play the" + card.name() + "\n" +
                                "The suit to play is " + Cr8z.suitToPlay);
                    }else {
                        JOptionPane.showMessageDialog(null, "Cannot Play the " + card.name() + "\n" +
                                "on the " + Cr8z.topOfDiscardPile.name());
                    }
                }
            }
        }else if(face == Face.Eight && !isInPlayerHand){
            JOptionPane.showMessageDialog(null,"Suit to play is "+Cr8z.suitToPlay);
        }
    }//..

    protected void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{

                        graphic.setLocation(p.x, p.y);
                        Thread.sleep(20);
                    }catch (Exception e){
                    }
                }
            }
        }).start();
    }//..

    protected static void selectSuitToPlay(){
        Cr8z.isPlayersTurn = false;
        final JFrame f = new JFrame();
        f.setLocationRelativeTo(Cr8z.panel);
        f.setTitle("Choose a Suit");
        f.setAlwaysOnTop(true);
        //f.setUndecorated(true);
        f.setLayout(new GridLayout());
        f.setSize(400,50);

        final JOptionPane jop = new JOptionPane();
        JButton hearts = new JButton("Hearts");
        hearts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cr8z.suitToPlay = Suit.Hearts;
                Cr8z.pause = false;
                f.setVisible(false);
            }
        });
        JButton diamonds = new JButton("Diamonds");
        diamonds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cr8z.suitToPlay = Suit.Diamonds;
                Cr8z.pause = false;
                f.setVisible(false);
            }
        });
        JButton clubs = new JButton("Clubs");
        clubs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cr8z.suitToPlay = Suit.Clubs;
                Cr8z.pause = false;
                f.setVisible(false);
            }
        });
        final JButton spades = new JButton("Spades");
        spades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cr8z.suitToPlay = Suit.Spades;
                Cr8z.pause = false;
                f.setVisible(false);
            }
        });
        f.add(spades);
        f.add(hearts);
        f.add(diamonds);
        f.add(clubs);
        f.setVisible(true);
    }//..

    public String name() {
        return face+" of "+suit;
    }
}// end Class Card





       /* jop.showOptionDialog(
                null,
                "Pick a Suit",
                "Man that 8 was Crazy",
                JOptionPane.OK_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{hearts,diamonds,clubs,spades},
                hearts
        );     */
