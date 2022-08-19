package gof.configurations;

import gof.GameOfLife;

public abstract class Injector implements IConfiguration {

    protected int data[];
    protected int width;
    protected int height;

    protected Injector(int[] data, int width, int height) {
        this.data = data;
        this.width = width;
        this.height = height;
    }

    protected Injector(){}

    protected abstract void inject(int xStart,int yStart,GameOfLife instance);
    protected abstract boolean isConfigurationValid(int xStart, int yStart, GameOfLife instance);

    protected void errorMessage(GameOfLife context){
        System.err.println("Configuration not valid check {xStart,yStart,width,height}");
        System.err.println("Grid Dimensions: {w: "+context.getGridWidth()+" h: "+context.getGridHeight()+" }");
    }
    public int[] getData() {
        return data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
