package Control;

import java.awt.*;
import java.awt.event.*;

public class MouseHandle implements MouseListener, KeyListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        Screen.getClick();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //get Request
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Screen.mousePos = new Point(e.getX()-(Settings.FRAME_WIDTH -Screen.width)/2, e.getY()-(Settings.FRAME_HEIGHT - Screen.height -(Settings.FRAME_WIDTH-Screen.width)/2));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Screen.mousePos = new Point(e.getX()-(Settings.FRAME_WIDTH -Screen.width)/2, e.getY()-(Settings.FRAME_HEIGHT - Screen.height -(Settings.FRAME_WIDTH-Screen.width)/2));
    }
}
