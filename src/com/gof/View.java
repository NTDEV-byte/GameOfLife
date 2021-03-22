package com.gof;

import java.awt.Color;
import java.awt.Graphics;

public class View {
	
	
			public static final int ALIVE = 1;
			public static final int DEAD = 0;
			public static final Color BACKGROUND_COL = Color.black;
			public static final Color LVCREATURES_COL = Color.yellow;
			public static final Color LINE_COL = Color.white;
			private int width;
			private int height;
			private int widthCell;
			private int heightCell;
			private int grid[];
 			
			
					public View(int width,int height) { 
						this.width = width;
						this.height = height;
						this.widthCell = 5;
						this.heightCell = 5;
						grid = new int[totalPerC() * totalPerL()];
					}
					
					
					public View(int width,int height,int wc,int hc) { 
						this.width = width;
						this.height = height;
						this.widthCell = wc;
						this.heightCell = hc;
						grid = new int[totalPerC() * totalPerL()];
					}
		
		
					
			public void showGrid(Graphics g) { 
						int cellsPerLine = totalPerL();
						int cellsPerCol = totalPerC();
						g.setColor(LINE_COL);
						for(int y = 0; y < cellsPerLine;y++) {
							 for(int x = 0; x < cellsPerCol; x++) { 
								   g.drawLine(x * widthCell, 0, x * widthCell, height);
							 }
							 g.drawLine(0, y * heightCell , width , y * heightCell);
						}
					}
		
		
			
			public void renderWorld(Graphics g) { 
				int cellsPerLine = totalPerL();
				int cellsPerCol = totalPerC();
				
				for(int y = 0; y < cellsPerLine;y++) {
					 for(int x = 0; x < cellsPerCol; x++) { 
						 	switch(grid[x + y * cellsPerCol]) { 
						 	case DEAD:
						 		g.setColor(BACKGROUND_COL);
						 		
						 		break;
						 	case ALIVE:
						 		g.setColor(LVCREATURES_COL);
						 		break;
						 	}
						 
						 	g.fillRect(x * widthCell, y * heightCell, widthCell, heightCell);
					 }
				}
			}
			
					
			public void setGridStateAt(int x,int y,int state) { 
				 if(validLocation(x,y)) { 
					 grid[x + y * totalPerC()] = state;
				 }
			}
			
			public int getStateGridAt(int x,int y) { 
				if(validLocation(x,y)) { 
					 return grid[x + y * totalPerC()];
				}
				return -1;
			}
					
			private boolean validLocation(int x,int y) { 
				 return x >= 0 && x < totalPerC()  && y >= 0 && y < totalPerL();
			}
					
			public int totalPerC() {
				return width / widthCell;
			}
			
			public int totalPerL() { 
				return height / heightCell;
			}


			public int[] getGrid() {
				return grid;
			}


			public void setGrid(int[] grid) {
				this.grid = grid;
			}
	
}
