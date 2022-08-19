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

    protected Injector(){
        this.friendlyReminderMessage();
    }

    protected abstract void inject(int xStart,int yStart,GameOfLife instance);
    protected boolean isConfigurationValid(int xStart,int yStart,GameOfLife instance) {
        return  xStart >= 0 && this.width < instance.getGridWidth()  &&
                yStart >= 0 && this.height < instance.getGridHeight() &&
                xStart + this.width < instance.getGridWidth() &&
                yStart + this.height < instance.getGridHeight();
    }

    private void friendlyReminderMessage(){
        System.out.println("If You See nothing after instantiating an injector ");
        System.out.println("Friendly reminder call the setUp method after construction : )");
        System.out.println("Hit space to start");
    }

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
