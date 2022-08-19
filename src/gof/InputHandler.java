package gof;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    private static InputHandler INSTANCE = null;
    private int mouseX,mouseY;
    private int mouseB;
    private boolean keys[]  = new boolean[2048];


    private InputHandler(){}


    public boolean isKeyDown(int keyCode){
        return keys[keyCode];
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
            mouseB = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
            mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseB() {
        return mouseB;
    }

    public static InputHandler getInstance(){
          if(INSTANCE == null){
              INSTANCE = new InputHandler();
          }
          return INSTANCE;
    }
}
