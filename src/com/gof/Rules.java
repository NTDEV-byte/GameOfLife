package com.gof;

public class Rules {
		
		private View v;
			
					public Rules(View v) { 
						this.v = v;
					}
					
					public void startLife(int withTotalOF) { 
						 int width = v.totalPerC();
						 int height = v.totalPerL();
						 int xRand,yRand;
						for(int i=0;i<withTotalOF;i++) { 
							xRand = (int)(Math.random() * width);
							yRand = (int)(Math.random() * height);
							v.setGridStateAt(xRand, yRand, View.ALIVE);
						}
					}
					
					public int getTotalNeighbors(int x,int y) { 
						int total = 0;
						if(v.getStateGridAt(x, y) == View.ALIVE) { 
							for(int nei=0;nei<9;nei++) { 
								 if(nei == 4) continue;
								 int xi = (nei % 3) - 1;
								 int yi = (nei / 3) - 1;
								 if(v.getStateGridAt(x + xi, y + yi) != -1) {
									 if(v.getStateGridAt(x + xi, y + yi) == View.ALIVE) {
										 total++;
									 }
								 }
							}
						}
						return total;
					}
					
					public void dieByIsolation(int x,int y) { 
						 int neighbors = getTotalNeighbors(x,y); 
						 if(neighbors == 0 || neighbors == 1) { 
							  	v.setGridStateAt(x, y, View.DEAD);
						 }
					}
					
					public void dieByOverPopulation(int x,int y) { 
						 int neighbors = getTotalNeighbors(x,y); 
						 if(neighbors >=  4) { 
							  	v.setGridStateAt(x, y, View.DEAD);
						 }
					}

					public void checkConstraints(int x,int y) { 
						dieByIsolation(x,y);
						dieByOverPopulation(x,y);
					}
					
					public void launchEvolution() { 
						for(int y=0;y<v.totalPerL();y++) {
							 for(int x=0;x<v.totalPerC();x++) {
								  	checkConstraints(x,y);
							 }
						}
					}

}
