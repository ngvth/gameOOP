package GameTile;

import Control.Settings;

import java.awt.*;

public class MapBlock extends Rectangle {
    private GameTile blockType;

    public MapBlock(int x, int y, int type){
        setBounds(x,y, Settings.BLOCK_SIDE,Settings.BLOCK_SIDE);
        if(type==Settings.MOUNTAIN_ID)
            blockType = new Mountain(x,y);
        else
            blockType = new Road(x,y);
    }

    public void draw(Graphics g){
        blockType.draw(g);
    }
}
