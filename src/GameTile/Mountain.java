package GameTile;

import Control.Settings;

import javax.swing.*;
import java.awt.*;

public class Mountain extends GameTile {
    private int x;
    private int y;
    private Tower tower;

    public Mountain(int x, int y){
        this.x = x;
        this.y = y;
        tower = null;
    }

    @Override
    public void draw(Graphics g){
        //g.setColor(Color.DARK_GRAY);
        //g.fillRect(x,y, Settings.BLOCK_SIDE,Settings.BLOCK_SIDE);
        Image image=new ImageIcon("images/mountion.jpg").getImage();
        g.drawImage(image,x,y,null);

    }
}
