package Control;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        setTitle("Game...");
        setSize(Settings.FRAME_WIDTH, Settings.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new Screen());
        setVisible(true);
        addMouseListener(new MouseHandle());
        addMouseMotionListener(new MouseHandle());
        addKeyListener(new MouseHandle());
    }

    public static void main(String[] args){
        GameFrame gameFrame = new GameFrame();
    }
}
