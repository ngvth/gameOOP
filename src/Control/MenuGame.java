package Control;

import javax.swing.*;
import java.awt.*;

import static Control.Screen.mousePos;

public class MenuGame extends JFrame {

    private Rectangle level1;
    private Rectangle level2;
    private static Image type1;
    private static Image type2;

    public MenuGame(){
        setTitle("Game...");
        setSize(Settings.FRAME_WIDTH, Settings.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addMouseListener(new MouseHandle());
        addMouseMotionListener(new MouseHandle());
        addKeyListener(new MouseHandle());
        level1= new Rectangle(300,100,100,50);
        level2= new Rectangle(300,200,100,50);
        type1 = new ImageIcon("images/level2.jpg").getImage();
        type2 = new ImageIcon("images/level2.jpg").getImage();
    }
    public  void  getclick(){
        if( level1.contains(mousePos))
        {
            //lv=1;
            GameFrame gameFrame= new GameFrame();
        }
        if(level2.contains(mousePos))
        {
            //lv=2
            GameFrame gameFrame=new GameFrame();
        }
    }
    //public ;

    public static void main(String[] args){
         MenuGame menuGame = new MenuGame();
    }
}
