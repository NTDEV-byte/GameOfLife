package gof.configurations;

import gof.GameOfLife;
import gof.Rules;

public class CustomInjector extends Injector
{

        public CustomInjector(int width,int height,int data[]){
                super(data,width,height);
        }

        @Override
        public void setUp(int xStart, int yStart,GameOfLife instance) {
                if(isConfigurationValid(xStart,yStart,instance)){
                        this.inject(xStart,yStart,instance);
                }
                else{
                        super.errorMessage(instance);
                }
        }

        @Override
        protected void inject(int xStart,int yStart,GameOfLife instance) {
              for(int y = yStart; y < yStart + this.height; y++){
                      for(int x = xStart; x <  xStart + this.width; x++){
                                instance.setGridStateAt(x ,y , Rules.ALIVE);
                      }
              }
        }

        @Override
        protected boolean isConfigurationValid(int xStart,int yStart,GameOfLife instance) {
                return   xStart >= 0 && this.width < instance.getGridWidth()  &&
                         yStart >= 0 && this.height < instance.getGridHeight() &&
                         xStart + this.width < instance.getGridWidth() &&
                         yStart + this.height < instance.getGridHeight();
        }

}
