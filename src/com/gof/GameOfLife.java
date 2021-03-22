package com.gof;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLife extends JPanel{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			public static final int SPEED_EVOLUTION = 80;
			public static final int TOTAL_LIVING_CREATURES = 1000;
			private View view;
			private Rules rules;
	
			public GameOfLife(int width,int height) { 
				setPreferredSize(new Dimension(width,height));
				view = new View(width,height,10,10);
				rules = new Rules(view);
			}
		
			public void paint(Graphics g) {
				 g.setColor(Color.white);
				 g.fillRect(0, 0, getWidth(), getHeight());
				 view.renderWorld(g);
				 view.showGrid(g);
				 rules.startLife(TOTAL_LIVING_CREATURES);
				 rules.launchEvolution();
				 try {
					Thread.sleep(SPEED_EVOLUTION);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				 repaint();
			}
			
			
			public static void main(String[] arg) {
				  GameOfLife gofView = new GameOfLife(800,600);
				  
				  JFrame window = new JFrame("GOF");
				  window.setVisible(true);
				  window.setResizable(false);
				  window.setLocationRelativeTo(null);
				  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				  window.add(gofView);
				  window.pack();
			}
}
