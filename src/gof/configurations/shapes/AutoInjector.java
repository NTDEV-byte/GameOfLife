package gof.configurations.shapes;

import gof.GameOfLife;
import gof.Rules;
import gof.configurations.Injector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class AutoInjector extends Injector {
    private static final int colorPixel = 0xff34B434;
    private String path;

    public AutoInjector(String path){
        this.path = path;
        this.loadImage();
    }

    @Override
    public void setUp(int xStart, int yStart, GameOfLife instance) {
            if(isConfigurationValid(xStart,yStart,instance)){
                    this.inject(xStart,yStart,instance);
            }
            else {
                    super.errorMessage(instance);
            }
    }
    @Override
    protected void inject(int xStart,int yStart,GameOfLife instance) {
        for(int y = yStart; y < yStart + this.height; y++){
            for(int x = xStart; x <  xStart + this.width; x++){
                if(data[(x - xStart) + (y - yStart) * this.width] == colorPixel){
                        instance.setGridStateAt(x ,y , Rules.ALIVE);
                }
            }
        }
    }
    private void loadImage(){
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream(path));
            width = image.getWidth();
            height = image.getHeight();
            data = new int[width * height];
            image.getRGB(0,0,width,height,data,0,width);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
