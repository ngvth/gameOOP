package GameTile;

import Control.Settings;
import java.awt.*;
import java.util.List;

public class Bullet {
    private int x;
    private int y;
    private int xStop;
    private int yStop;
    private int dame;
    private boolean check;


    public Bullet(int x, int y, int dame, Enemy target) {
        this.x = x;
        this.y = y;
        this.dame = dame;
        xStop = x + (target.getX() - x) * 100;
        yStop = y + (target.getY() - y) * 100;
        check = true;
    }

    public void setDame(int dame) {
        this.dame = dame;
    }


    public boolean isCheck() {
        return check;
    }

    public void move() {

        int d = (int) Math.sqrt( Math.pow(xStop - x, 2) + Math.pow(yStop - y, 2));
        if(d<5) {
            check = false;
            return;
        }
        x += 25* (xStop-x)/d;
        y += 25* (yStop-y)/d;
    }

    public void checkCollision(List<Enemy> enemies) {
        if(enemies!=null)
        for (Enemy enemy : enemies) {
            if (new Rectangle(x, y, 20, 20).intersects(new Rectangle(enemy.getX(), enemy.getY(), Settings.ENEMY_SIZE, Settings.ENEMY_SIZE))) {
                check = false;
                enemy.setHp(enemy.getHp()-dame);
                System.out.println(enemy.getHp());
                setDame(0);
                return;
            }
        }
    }
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x,y,20,20);
    }
}
