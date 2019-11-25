package GameTile;

import Control.Player;
import Control.Screen;
import Control.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameMap {
    public MapBlock[][] blocks;
    public Boolean[][] free;
    public GameMap(String filename){
        blocks = new MapBlock[Settings.MAP_HEIGHT_SIZE][Settings.MAP_WIDTH_SIZE];
        free = new Boolean[Settings.MAP_HEIGHT_SIZE][Settings.MAP_WIDTH_SIZE];

        try{
            Scanner forMapData = new Scanner(new File(filename));
            for(int i=0; i<Settings.MAP_HEIGHT_SIZE; i++){
                for(int j=0; j<Settings.MAP_WIDTH_SIZE; j++){
                    int type = forMapData.nextInt();
                    blocks[i][j] = new MapBlock(j*Settings.BLOCK_SIDE, i*Settings.BLOCK_SIDE, type);
                    free[i][j] = true;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
        for(int i=0; i<Settings.MAP_HEIGHT_SIZE; i++)
            for(int j=0; j<Settings.MAP_WIDTH_SIZE; j++)
                blocks[i][j].draw(g);
        }

    public Tower checkBuy(int type){
        for(int i=0; i<Settings.MAP_HEIGHT_SIZE; i++)
            for(int j=0; j<Settings.MAP_WIDTH_SIZE; j++)
                if((blocks[i][j].contains(Screen.mousePos))&&(free[i][j])) {
                    free[i][j] = false;
                    if (type == 1)
                    {
                        if (Player.money>=10) {
                            return new Tower1(j * Settings.BLOCK_SIDE, i * Settings.BLOCK_SIDE, Settings.DAME_TOWER1, new ImageIcon("images/tower.jpg").getImage());
                        }
                        else
                            return  null;
                    }
                    else
                    if (type==2) {
                        if (Player.money>=20) {
                            return new Tower2(j * Settings.BLOCK_SIDE, i * Settings.BLOCK_SIDE, Settings.DAME_TOWER2, new ImageIcon("images/tower2.jpg").getImage());
                        }
                        else return null;
                    } else if (type == 3) {
                        if (Player.money>=30) {
                            return new Tower3(j * Settings.BLOCK_SIDE, i * Settings.BLOCK_SIDE, Settings.DAME_TOWER3, new ImageIcon("images/tower3.jpg").getImage());
                        }
                        else return null;
                    }
                }
        return null;
    }
}
