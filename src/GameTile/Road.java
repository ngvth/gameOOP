package GameTile;

import Control.Settings;

import javax.swing.*;
import java.awt.*;

public class Road extends GameTile {
    private int x;
    private int y;
    private  Road NextRoad;

    public Road(int x, int y){
        this.x = x;
        this.y = y;
        NextRoad = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

   public Road getNextRoad()
   {
       return NextRoad;
   }

    public void setNextRoad(Road nextRoad) {
        NextRoad = nextRoad;
    }

    @Override
    public void draw(Graphics g){
        Image image=new ImageIcon("images/road.jpg").getImage();
        g.drawImage(image,x,y,null);
    }
}
