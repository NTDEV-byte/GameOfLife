package gof.configurations;

import gof.GameOfLife;

public abstract class SetupInjector implements IConfiguration {

    public abstract void setUp(int x,int y,GameOfLife instance);

}
