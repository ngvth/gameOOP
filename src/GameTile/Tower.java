package GameTile;
import Control.Settings;
import java.util.List;
import java.awt.*;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.sqrt;

public abstract class Tower extends GameTile {
    private int x;
    private int y;
    private Image image;
    int dame;


    public Tower(int x, int y, int dame,Image image) {
        this.x = x;
        this.y = y;
        this.dame = dame;
        this.image=image;

    }

    public Bullet Create_Bullets(List<Enemy> enemyList) {
        for (Enemy enemy : enemyList)
            if (checkshooting(enemy)) {
                Bullet bullet = new Bullet(x + Settings.ENEMY_SIZE / 2, y + Settings.ENEMY_SIZE / 2, dame, enemy);
                return bullet;
            }
        return null;
    }

    public int range(Enemy enemy) {
        double a = abs(enemy.getX() - x) * abs(enemy.getX() - x) + abs(enemy.getY() - y) * abs(enemy.getY() - y);
        return (int) sqrt(a);
    }
    public abstract Boolean checkshooting(Enemy enemy);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,x,y,null);
        g.setColor(Color.WHITE);
        //g.drawOval(x-Settings.SHOOTING_RANGE_TOWER1+25,y-Settings.SHOOTING_RANGE_TOWER1+25,Settings.SHOOTING_RANGE_TOWER1*2,Settings.SHOOTING_RANGE_TOWER1*2);
    }
}
