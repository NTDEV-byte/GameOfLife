package gof;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JPanel {

        // grid related
        private int width = 80;
        private int height = width * 9 / 16;
        private int blockSize = 8;
        private int grid[] = new int[width * height];;

        // screen related
        private int screenWidth = width * blockSize;
        private int screenHeight = height * blockSize;

        // thread
        private boolean running;
        private Thread thread;

        // simulation
        private Color gridColor = new Color(0x801D80),
                      aliveCellColor = new Color(0x34B434),
                      deadCellColor = new Color(0xFF0045);
        private Rules rules;


        public static void main(String args[]){
                JFrame window = new JFrame("GameOfLife");
                window.setVisible(true);
                window.setResizable(false);
                window.setLocationRelativeTo(null);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.add(new GameOfLife());
                window.pack();

        }

        public GameOfLife() {
                this.init();
                this.start();
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

                this.rules = new Rules(this);
                this.setUpGameLoop();
                setGridStateAt(5,5,Rules.ALIVE);
                setGridStateAt(6,5,Rules.ALIVE);
                setGridStateAt(7,5,Rules.ALIVE);
        }


        // logic
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

        private void update(){
                rules.apply();
        }
        public int getGridStateAt(int x,int y){
                if(withInBounds(x,y)){
                      return grid[x + y * width];
                }
                return Rules.DEAD;
        }
        public void setGridStateAt(int x,int y,int state){
                if(withInBounds(x, y)){
                        grid[x + y * width] = state;
                }
        }
        private boolean withInBounds(int x,int y){
                return x >= 0 && x < width && y >= 0 && y < height;
        }

        // drawing
        @Override
        public void paint(Graphics g){
                render(g);
        }
        private void render(Graphics g){
                this.clearScreen(g);
                this.reflectGridState(g);
                this.drawGrid(g);
        }
        private void drawGrid(Graphics g){
                g.setColor(gridColor);
                for(int y = 0; y < height ; y++){
                        for(int x = 0; x < width; x++){
                                g.drawLine(x * blockSize , 0 , x * blockSize , screenHeight);
                        }
                        g.drawLine(0 , y * blockSize , screenWidth , y * blockSize);
                }
        }
        private void clearScreen(Graphics g){
                g.setColor(Color.black);
                g.fillRect(0,0,getWidth(),getHeight());
        }

        private void reflectGridState(Graphics g){
                for(int y = 0; y < height; y++){
                        for(int x = 0; x < width; x++){
                               if(getGridStateAt(x , y) == Rules.ALIVE){
                                       g.setColor(aliveCellColor);
                               }
                               else{
                                       g.setColor(deadCellColor);
                               }
                               g.fillRect(x * blockSize , y * blockSize , blockSize, blockSize);
                        }
                }
        }

        public int getGridWidth(){
                return width;
        }

        public int getGridHeight(){
                return height;
        }



}

