package gof.configurations;

public abstract class Injector implements IConfiguration {

    protected int data[];
    protected int width;
    protected int height;


    protected Injector(int[] data, int width, int height) {
        this.data = data;
        this.width = width;
        this.height = height;
    }

    protected abstract boolean isConfigurationValid();


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
