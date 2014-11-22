package cr8z;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Kevin on 11/5/2014.
 */
public class CardBack {

    ImageIcon img = null,gone;
    JLabel back;
    Position p;
    boolean isDeckCover = false;
    int w = 60,
        h = 80;


    CardBack(Position p) {
        try {

            this.p = p;


            gone = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/cr8z/img/empty1.png")));
            gone = ScaleIMageIcon.scale(gone,w,h);

            img = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/cr8z/img/back.jpg")));
            img = ScaleIMageIcon.scale(img,w,h);

            back = new JLabel(img);
            back.setBounds(p.x,p.y,w,h);
            Cr8z.panel.add(back);

            startThread();
        } catch (IOException e) {
            e.printStackTrace();
        }

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clickLogic();
            }
        });

    }//..

    private void clickLogic(){
        if(isDeckCover && Cr8z.isPlayersTurn && Cr8z.player.hand.size() < 24){
            if(Cr8z.deck.size() > 0) {
                Cr8z.player.takeCard(Cr8z.deck.get(0));
                Cr8z.deck.remove(0);
            }else {

                JOptionPane.showMessageDialog(null,"There are no more cards\n" +
                        "You pass this turn");
                Cr8z.isPlayersTurn = false;
                back.setIcon(gone);
            }
        }
    }//..


    protected void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{

                        back.setLocation(p.x, p.y);
                        Thread.sleep(20);
                    }catch (Exception e){
                    }
                }
            }
        }).start();
    }//..



}// end Class CardBack
