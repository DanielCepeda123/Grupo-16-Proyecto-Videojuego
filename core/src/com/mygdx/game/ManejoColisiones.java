package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ManejoColisiones {
	private CampoAsteroides campoAsteroides;
	private ArrayList<Bullet> balas;
	private CoordinadorSonidos sonidos;
	private int score;
	private Nave4 nave;
	private SpriteBatch batch;
	
	public ManejoColisiones(CampoAsteroides campoAsteroides, ArrayList<Bullet> balas, 
							CoordinadorSonidos sonidos, int score, 
							Nave4 nave, SpriteBatch batch) {
		this.balas = balas;
		this.campoAsteroides = campoAsteroides;
		this.sonidos = sonidos;
		this.score = score;
		this.nave = nave;
		this.batch = batch;
	}
	
	public int colisionBalaAsteroide() {
		for (int i = 0; i < balas.size(); i++) {
            Bullet bala = balas.get(i);
            bala.update(); 
            for (int j = 0; j < campoAsteroides.getBalls1Size(); j++) {
            	if (bala.checkCollision(campoAsteroides.getAsteroide1(j))) {          
        		 sonidos.playExplosionSound();
            	 campoAsteroides.removerAsteroide(j);
            	 j--;
            	 score +=10;
              }   	  
  	        }
                
            if (bala.isDestroyed()) {
                balas.remove(bala);
                i--; //para no saltarse 1 tras eliminar del arraylist
            }
      }
		return score;
	}
	
	public void colisionAsteroides() {
		for (int i=0;i<campoAsteroides.getBalls1Size();i++) {
	    	Ball2 ball1 = campoAsteroides.getAsteroide1(i);   
	        for (int j=0;j<campoAsteroides.getBalls2Size();j++) {
	          Ball2 ball2 = campoAsteroides.getAsteroide2(j); 
	          if (i<j) {
	        	  ball1.checkCollision(ball2);
	     
	          }
	        }
	      } 
	}
	
	public void colisionNaveAsteroide() {
		for (int i = 0; i < campoAsteroides.getBalls1Size(); i++) {
    	    Ball2 b=campoAsteroides.getAsteroide1(i);
    	    b.draw(batch);
	          //perdió vida o game over
              if (nave.checkCollision(b)) {
	            //asteroide se destruye con el choque             
            	 campoAsteroides.removerAsteroide(i);
            	 i--;
          }   	  
	        }  
	}
}
