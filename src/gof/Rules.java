package gof;

public class Rules {

        public static final int ALIVE = 1;
        public static final int DEAD = 0;

        private GameOfLife gameOfLife;
        public Rules(GameOfLife gameOfLife){
            this.gameOfLife = gameOfLife;
        }

        public void receiveGridState(){

        }
        public void transferGridState(){

        }
        private void applyEvolution(){

        }
        private int neighboursCounter(int x,int y){
                return -1;
        }
        private void newBorn(){}
        private void dieByIsolation(){}
        private void dieByOverPopulation(){}
}
