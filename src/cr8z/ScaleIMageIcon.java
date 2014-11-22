package cr8z;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 10/11/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScaleIMageIcon {

    public static ImageIcon scale(ImageIcon icon, int w, int h){
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( w, h,  Image.SCALE_SMOOTH ) ;
        return new ImageIcon( newimg );
    }//..

}// ens ScaleImage
