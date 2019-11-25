package GameTile;

import Control.Settings;

import javax.swing.*;
import java.awt.*;

public class Boss extends Enemy {

    Image image=new ImageIcon("images/Boss.jpg").getImage();
    public Boss(Road road) {
        super(road);
        this.setHp(Settings.BOSS_HP);
        this.setSpeed(Settings.SPEED_ENEMY);
    }
    public void draw(Graphics g){
        g.drawImage(image,super.getX(),super.getY(),null);
        g.setColor(Color.RED);
        g.fillRect(super.getX(),super.getY(),super.getHp()/4,2);
    }
}
