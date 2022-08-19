package gof;

public class Rules {

        public static final int ALIVE = 1;
        public static final int DEAD = 0;

        private GameOfLife gameOfLife;
        private int nextGeneration[];

        public Rules(GameOfLife gameOfLife){
            this.gameOfLife = gameOfLife;
            this.nextGeneration = new int[this.gameOfLife.getGridWidth() * this.gameOfLife.getGridHeight()];
        }

        public void receiveGridState(){
            for(int y = 0; y < gameOfLife.getGridHeight(); y++){
                for(int x = 0; x  < gameOfLife.getGridHeight(); x++){
                       nextGeneration[x + y * gameOfLife.getGridWidth()] = gameOfLife.getGridStateAt(x , y);
                }
            }
        }
        public void transferGridState(){
            for(int y = 0; y < gameOfLife.getGridHeight(); y++){
                for(int x = 0; x  < gameOfLife.getGridWidth(); x++){
                     gameOfLife.setGridStateAt(x , y,nextGeneration[x +  y * gameOfLife.getGridWidth()]);
                }
            }
        }
        public void apply(){
            receiveGridState();
              for(int y = 0; y < gameOfLife.getGridHeight(); y++){
                      for(int x = 0; x < gameOfLife.getGridWidth(); x++){
                          evolutionRules(x , y);
                      }
              }
              transferGridState();
        }
        private void evolutionRules(int x,int y){
                newBorn(x , y);
                dieByIsolation(x , y);
                dieByOverPopulation(x, y);
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
                        nextGeneration[x + y * gameOfLife.getGridWidth()] = ALIVE;
               }
        }
        private void dieByIsolation(int x,int y){
                if(countAliveNeighbours(x , y) < 2 && gameOfLife.getGridStateAt(x , y) == ALIVE){
                        nextGeneration[x + y * gameOfLife.getGridWidth()] = DEAD;
                }
        }
        private void dieByOverPopulation(int x,int y){
                if(countAliveNeighbours(x , y) > 3 && gameOfLife.getGridStateAt(x , y) == ALIVE){
                        nextGeneration[x + y * gameOfLife.getGridWidth()] = DEAD;
                }
        }
}
