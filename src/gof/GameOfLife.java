package gof;

import gof.configurations.CustomInjector;
import gof.configurations.IConfiguration;
import gof.configurations.shapes.AutoInjector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameOfLife extends JPanel {

        private static final InputHandler instance = InputHandler.getInstance();
        private static final int MAX_FPS = 120;
        private static final int OneSecond = 1000;

        // grid related

        private int width = 80;
        private int height = width * 9 / 16; // 45
        private int blockSize = 8;
        private int grid[];

        // screen related
        private int screenWidth = width * blockSize;
        private int screenHeight = height * blockSize;

        // thread
        private boolean running;
        private Thread thread;

        // simulation
        private Color gridColor      = new Color(0x801D80),
                      aliveCellColor = new Color(0x34B434),
                      deadCellColor  = new Color(0xFF0045);
        private Rules rules;
        private boolean paused = true;
        private double minWait = 200,elapsedTime;
        private IConfiguration configuration;

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
                this.addKeyListener(instance);
                this.addMouseListener(instance);
                this.addMouseMotionListener(instance);
                this.setUpGameLoop();
                this.grid = new int[width * height];
                this.rules = new Rules(this);
                this.injectorSetUpConfig();
        }


        private void injectorSetUpConfig(){
                // auto
                this.configuration = new AutoInjector("./1-2-3-4.png");
                this.configuration.setUp(5,5,this);

                this.configuration = new AutoInjector("./1-2-3.png");
                this.configuration.setUp(35,5,this);

                // custom
//                this.configuration = new CustomInjector(5,5,new int[]{
//                        1,1,1,1,1,
//                        1,1,1,1,1,
//                        1,1,1,1,1,
//                        1,1,1,1,1,
//                });
//                this.configuration.setUp(5,5,this);
        }
        // logic
        private void setUpGameLoop(){
                thread = new Thread(() -> {
                        while(running){
                                update();
                                fpsLimiter(MAX_FPS);
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
                this.simulation();
                this.mouseInteraction();
                this.requestFocus();
        }

        private void simulation(){
                if(!paused){
                        this.rules.apply();
                }
                this.pauseAndResume();
        }

        private void pauseAndResume(){
                if(instance.isKeyDown(KeyEvent.VK_SPACE) && elapsedTime >= minWait){
                        paused = !paused;
                        this.elapsedTime = 0;
                        if(elapsedTime >= 1000 * 120) elapsedTime = 0;
                        System.out.println("One Press !");
                }
                this.minWaiter();
        }

        private void minWaiter(){
                elapsedTime += OneSecond / MAX_FPS;
        }
        private void mouseInteraction(){
                makeCellDeadOnRightMouseClick();
                makeCellAliveOnLeftMouseClick();
        }
        private void makeCellAliveOnLeftMouseClick(){
                if(instance.getMouseB() == MouseEvent.BUTTON1){
                          setGridStateAt(getMouseXLoc() , getMouseYLoc() , Rules.ALIVE);
                }
        }

        private void makeCellDeadOnRightMouseClick(){
                if(instance.getMouseB() == MouseEvent.BUTTON3){
                        setGridStateAt(getMouseXLoc() , getMouseYLoc() , Rules.DEAD);
                }
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

        private int getMouseXLoc(){
                return instance.getMouseX() / blockSize;
        }

        private int getMouseYLoc(){
                return instance.getMouseY() / blockSize;
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

