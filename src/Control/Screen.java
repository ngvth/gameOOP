package Control;

import GameTile.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

import static Control.Player.*;


public class Screen extends JPanel implements Runnable {
    private static GameMap map;
    public Thread thread;
    private static Player player = new Player();
    public static int wait=-10;
    public static int dem=0;

    private static Rectangle buyButton1;
    private static Rectangle buyButton2;
    private static Rectangle buyButton3;
    private  static Rectangle buyButton4;
    private static int waitBuy;

    private static Image waitingBuy;
    private static Image type1;
    private static Image type2;
    private static Image type3;
    private static Image type4;

    public static Point mousePos;
    public static int width;
    public static int height;
    private boolean Initialized;

    public  List<Road> listRoad;

    public void loadRoad(String filename){
        listRoad = new ArrayList<>();

        int[][] mapVal = new int[Settings.MAP_HEIGHT_SIZE][Settings.MAP_WIDTH_SIZE];
        try{
            Scanner forMapData = new Scanner(new File(filename));
            for(int i=0; i<Settings.MAP_HEIGHT_SIZE; i++){
                for(int j=0; j<Settings.MAP_WIDTH_SIZE; j++){
                    mapVal[i][j] = forMapData.nextInt();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        boolean check[][]= new boolean[Settings.MAP_HEIGHT_SIZE][Settings.MAP_HEIGHT_SIZE];
        for (int i=0; i<Settings.MAP_HEIGHT_SIZE; i++)
            for (int j=0; j<Settings.MAP_WIDTH_SIZE; j++)
            {
                if (mapVal[i][j]==0) check[i][j]=true;
            }

        boolean kt=true;
        for (int i=0; i<Settings.MAP_WIDTH_SIZE; i++) {
            for (int j = 0; j < Settings.MAP_HEIGHT_SIZE; j++)
                if (mapVal[i][j] == 2) {
                    Road road = new Road(j * Settings.BLOCK_SIDE, i * Settings.BLOCK_SIDE);
                    listRoad.add(road);
                    kt = false;
                    while (true) {
                        if ((mapVal[i + 1][j] == 1) && (check[i+1][j] == false)) {
                            i++;
                            road = new Road(j * Settings.BLOCK_SIDE, i * Settings.BLOCK_SIDE);
                            listRoad.add(road);
                            check[i][j] = true;
                            continue;
                        }

                        if ((mapVal[i][j + 1] == 1) && (check[i][j + 1] == false)) {
                            j++;
                            road = new Road(j * Settings.BLOCK_SIDE, i * Settings.BLOCK_SIDE);
                            listRoad.add(road);
                            check[i][j] = true;
                            continue;
                        }
                        if (i>0)
                        if ((mapVal[i-1][j] == 1) && (check[i-1][j] == false)) {
                            i--;
                            road = new Road(j * Settings.BLOCK_SIDE, i * Settings.BLOCK_SIDE);
                            listRoad.add(road);
                            check[i][j] = true;
                            continue;
                        }
                        if (j>0)
                        if ((mapVal[i][j-1] == 1) && (check[i][j -1] == false)) {
                            j--;
                            road = new Road(j * Settings.BLOCK_SIDE, i * Settings.BLOCK_SIDE);
                            listRoad.add(road);
                            check[i][j] = true;
                            continue;
                        }
                         break;
                    }



                }

            if (!kt) break;
        }

        for(int i=0; i<listRoad.size()-1; i++)
            listRoad.get(i).setNextRoad(listRoad.get(i+1));
    }

    public Screen(){
        thread = new Thread(this);
        map = new GameMap("src\\GameTile\\mapData2.txt");
        thread.start();
        loadRoad("src\\GameTile\\mapData2.txt");
        player = new Player();
        player.addEnemy(new Enemy1(listRoad.get(0)));
        buyButton1 = new Rectangle(610,100,50,50);
        buyButton2 = new Rectangle(610,200,50,50);
        buyButton3 = new Rectangle(610,300,50,50);
        buyButton4 = new Rectangle(610,400,50,50);
        Initialized = false;
        waitBuy = 0;
        type1 = new ImageIcon("images/tower.jpg").getImage();
        type2 = new ImageIcon("images/tower2.jpg").getImage();
        type3 = new ImageIcon("images/tower3.jpg").getImage();
        type4 = new  ImageIcon("images/road.jpg").getImage();
    }

    public static void getClick(){
        if(buyButton1.contains(mousePos)) {
            waitBuy = 1;
            waitingBuy = type1;
        }
        else if(buyButton2.contains(mousePos)) {
            waitBuy = 2;
            waitingBuy = type2;
        }
        else if(buyButton3.contains(mousePos))
        {
            waitBuy=3;
            waitingBuy=type3;
        }

        if(waitBuy == 0)
            return;

        if(buyButton4.contains(mousePos))
        {
            waitBuy=0;
            return;
        }

        Tower t = map.checkBuy(waitBuy);
        if(t!=null) {
            player.addTower(t);
            if (t instanceof Tower3)
            {
                money=money-30;
            }
            else {
                if (t instanceof  Tower1)
                    money -= 10;
                else
                    money-=20;
            }
            waitBuy = 0;
        }
    }

    public void drawStore(Graphics g){
        if (player.ingame) {
            g.drawImage(type1, buyButton1.x, buyButton1.y, null);
            g.drawImage(type2, buyButton2.x, buyButton2.y, null);
            g.drawImage(type3, buyButton3.x, buyButton3.y, null);
            g.drawImage(type4,buyButton4.x,buyButton4.y,null);
            g.setColor(Color.YELLOW);
            if (waitBuy != 0) {
                g.drawImage(waitingBuy, mousePos.x - 25, mousePos.y - 25, null);
                g.drawOval(mousePos.x - Settings.SHOOTING_RANGE_TOWER1, mousePos.y - Settings.SHOOTING_RANGE_TOWER1, Settings.SHOOTING_RANGE_TOWER1 * 2, Settings.SHOOTING_RANGE_TOWER1 * 2);
            }
            g.drawString("MONEY : "+money+" ",650,50);
            g.drawString("You HP : "+hpTowerMain,650,75);
            g.drawString("tower 1 ",670,110);
            g.drawString("money : 10",670,125);
            g.drawString("dame : 1",670,140);

            g.drawString("tower 2",670,210);
            g.drawString("money : 20",670,225);
            g.drawString("dame : 2",670,240);

            g.drawString("tower 3",670,310);
            g.drawString("money : 30",670,325);
            g.drawString("dame : 3",670,340);

        }
    }

    public void paintComponent(Graphics g){
        if(!Initialized){
            width = getWidth();
            height = getHeight();
        }
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,width,height);
        map.draw(g);
        player.draw(g);
        drawStore(g);
    }

    @Override
    public void run() {
        while(player.ingame) {
            if(wait>=150 && dem<=100) // tạo ra các Enemy
            {
                Boss lastBoss=null;
                Random rd = new Random();
                int number1 = rd.nextInt(3);	// trả về 1 số nguyên nằm trong phạm vi [0...3)
                if (dem%20==0&& dem>=20)
                {
                    Boss b= (new Boss(listRoad.get(0)));
                    player.addEnemy(b);
                    if (dem>=100) lastBoss=b;

                }
                else
                if (number1==1)
                    player.addEnemy(new Enemy2(listRoad.get(0)));
                else
                    player.addEnemy(new Enemy1(listRoad.get(0)));
                dem++;
                wait =0;
                if (dem==50&& lastBoss.getHp()<=0 )
                    ingame = false;
            }
            else wait ++;

            player.makeMove();
            repaint();
            try {
                thread.sleep(10);
            } catch (Exception e) {
            }
        }

    }
}
