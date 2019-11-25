package Control;

import javax.swing.*;
import java.awt.*;

public class gameover {
    public  void drawGameover(Graphics g)
    {
        Image image =new ImageIcon("images/gameover.png").getImage();
        g.drawImage(image,0,0,null);
    }
}