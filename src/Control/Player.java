package Control;

import GameTile.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public static boolean ingame;
    private List<Enemy> listEnemy;
    private List<Tower> listTower;
    private List<Bullet> listBullet;
    public static int money;
    public  static int hpTowerMain;

    //forTest
    int wait = 0;

    public Player() {
        listEnemy = new ArrayList<>();
        listTower = new ArrayList<>();
        listBullet = new ArrayList<>();
        money=50;
        hpTowerMain =Settings.HP_TOWER_MAIN;
        ingame=true;
    }

    public void draw(Graphics g) {
        if (ingame)
        {
            for (Enemy e : listEnemy)
                e.draw(g);
            for (Tower t : listTower)
                t.draw(g);
            for (Bullet b : listBullet)
                b.draw(g);

        }
        else{
            Image image =new ImageIcon("images/gameover.png").getImage();
            g.drawImage(image,0,0,null);
        }
    }

    public void makeMove() {
        List<Enemy> kill = new ArrayList<>();
        for (Enemy e : listEnemy)// trừ tiền và xóa những Enemy chết
        {
            if (e.getHp() <= 0)
            {
                kill.add(e);
                if (e instanceof Boss)
                    money+=5;
                else
                    if (e instanceof Enemy1)
                        money+=1;
                    else money+=2;
            }
            else
                e.move();
        }

        listEnemy.removeAll(kill);

        for(Bullet b:listBullet)
            b.checkCollision(listEnemy);
        
        List<Bullet> kill2 = new ArrayList<>();
        for (Bullet b : listBullet) {
            if (!b.isCheck())
                kill2.add(b);
            else
                b.move();
        }
        listBullet.removeAll(kill2);

        if ((listEnemy.size() > 0)&&(wait>=20) ){
            for (Tower tower : listTower) {
                Bullet bullet = tower.Create_Bullets(listEnemy);
                if (bullet != null) addBullet(bullet);
            }
            wait = 0;
        }
        wait++;
    }


    public void addTower(Tower t) {
        listTower.add(t);
    }

    public void addEnemy(Enemy e) {
        listEnemy.add(e);
    }

    public void addBullet(Bullet b) {
        listBullet.add(b);
    }
}
