package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DisparoRapido implements StrategyDisparo{
	private Sound soundBala;
    private Texture txBala;
    private Sprite spr;
    private PantallaJuego juego;
	
	
	public DisparoRapido(Sound soundBala, Texture txBala, Sprite spr, PantallaJuego juego) {
		this.soundBala = soundBala;
		this.txBala = txBala;
		this.spr = spr;
		this.juego = juego;
	}
	
	public void disparo() {
		Bullet  bala = new Bullet(spr.getX()+spr.getWidth()/2-5,spr.getY()+ spr.getHeight()-5,0,10,txBala);
	      juego.agregarBala(bala);
	      soundBala.play();
	}
	
	
}
