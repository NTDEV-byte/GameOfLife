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
        private int countAliveNeighbours(int x,int y){
             int total = 0;
             for(int i = 0; i < 9; i++){
                     if(i == 4) continue;
                     int xi = (i % 3) - 1;
                     int yi = (i / 3) - 1;
                     if(gameOfLife.getGridStateAt(x + xi , y + yi) == ALIVE){
                             total++;
                     }
             }
                return total;
        }


        private void newBorn(int x,int y){
               if(countAliveNeighbours(x , y) == 3 && gameOfLife.getGridStateAt(x , y) == DEAD){
                        gameOfLife.setGridStateAt(x , y , ALIVE);
               }
        }
        private void dieByIsolation(int x,int y){
                if(countAliveNeighbours(x , y) < 3 && gameOfLife.getGridStateAt(x , y) == ALIVE){
                        gameOfLife.setGridStateAt(x , y , DEAD);
                }
        }
        private void dieByOverPopulation(int x,int y){
                if(countAliveNeighbours(x , y) > 3 && gameOfLife.getGridStateAt(x , y) == ALIVE){
                        gameOfLife.setGridStateAt(x , y , DEAD);
                }
        }
}
