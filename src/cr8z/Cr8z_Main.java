package cr8z;

import javax.swing.*;

/**
 * Created by Kevin on 11/5/2014.
 */
public class Cr8z_Main extends JFrame {

    Cr8z_Main(){
        setSize(600,500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cr8z");
        PlaySound.play(getClass(),"/cr8z/snd/bgm.wav");
        setResizable(false);
        setContentPane(new Cr8z());
        setVisible(true);
    }//..

    public static void main(String[] args) {
        new Cr8z_Main();
    }//..

}// end Class Cr8z
