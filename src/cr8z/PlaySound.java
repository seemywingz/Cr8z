package cr8z;

import sun.audio.AudioPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 10/11/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaySound {

    public static void play(Class c,String path){
        AudioPlayer.player.start(c.getResourceAsStream(path));
    }//..

}// end PlaySound
