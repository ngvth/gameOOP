package GameTile;
import java.awt.*;
import static Control.Player.hpTowerMain;
import static Control.Player.ingame;

public abstract class Enemy {
    private int x;
    private int y;
    private int hp;
    private int Speed;
    Road road;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Enemy(Road road){
        this.road = road;
        this.x = road.getX();
        this.y = road.getY();
    }

    public abstract void draw(Graphics g);

    public void setSpeed(int speed) {
        Speed = speed;
    }

    public  void move() {
        Road nextRoad = road.getNextRoad();

        if(nextRoad==null) {
            hpTowerMain-=hp;
            if(hpTowerMain<=0)
            {
                System.out.println("HP TOWER"+hpTowerMain);
                //drawSting();
                ingame=false;
            }
            hp=0;
            return;
        }

        if (nextRoad.getX() > x)
            x += Speed;
        if(nextRoad.getX() < x)
            x -= Speed;
        if (nextRoad.getY() > y)
            y += Speed;
        if(nextRoad.getY() < y)
            y -= Speed;

        if((x==nextRoad.getX())&&(y==nextRoad.getY())){
            road=nextRoad;
        }
    }

}
