package gof;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JPanel {

        // grid related
        private int width = 80;
        private int height = 60;
        private int blockSize = 4;
        private int grid[] = new int[width * height];

        // screen related
        private int screenWidth = width * blockSize;
        private int screenHeight = height * blockSize;

        // simulation
        private boolean running;
        private Thread thread;

        public GameOfLife() {
                this.init();
        }

        public void start(){
                running = true;
                thread.start();
        }

        public void stop(){
                running = false;
        }

        private void init(){
                this.setPreferredSize(new Dimension(screenWidth , screenHeight));
                this.setUpGameLoop();
        }

        private void setUpGameLoop(){
                thread = new Thread(() -> {
                        while(running){
                                update();
                                fpsLimiter(60);
                                repaint();
                        }
                } , "Game-Of-Life-Thread");

        }

        private void fpsLimiter(int maxFPS){
                try {
                        Thread.sleep(1000 / maxFPS);
                } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                }
        }

        private void update(){}

        @Override
        public void paint(Graphics g){
                render(g);
        }

        private void render(Graphics g){
                clearScreen(g);
        }

        private void clearScreen(Graphics g){
                g.setColor(Color.black);
                g.fillRect(0,0,getWidth(),getHeight());
        }

}

