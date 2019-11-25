package GameTile;


import Control.Settings;

import java.awt.*;

public class Tower3 extends Tower {
    public Tower3(int x, int y, int dame,Image image) {
        super(x,y,dame,image);

    }
    public Boolean checkshooting(Enemy enemy) {

        if (range(enemy) <= Settings.SHOOTING_RANGE_TOWER3)
            return true;
        return false;
    }
}
