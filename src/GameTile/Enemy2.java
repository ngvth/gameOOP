package GameTile;

import Control.Settings;

import javax.swing.*;
import java.awt.*;

public class Enemy2 extends Enemy {

    Image image=new ImageIcon("images/enemy2.jpg").getImage();
    public Enemy2(Road road) {
        super(road);
        this.setHp(Settings.HP_ENYMY2);
        this.setSpeed(Settings.SPEED_ENEMY);

    }
    public void draw(Graphics g){
        g.drawImage(image,super.getX(),super.getY(),null);
        g.setColor(Color.RED);
        g.fillRect(super.getX(),super.getY(),super.getHp()/2,2);
    }
}
