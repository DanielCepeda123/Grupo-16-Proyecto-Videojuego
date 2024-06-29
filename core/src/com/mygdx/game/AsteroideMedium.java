package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AsteroideMedium implements BuilderAsteroide{
	private Ball2 asteroide;
	Random random = new Random();
	
	public AsteroideMedium() {
		this.asteroide = new Ball2();
		
	}
	
	public void buildVelocidad(int velXAsteroides, int velYAsteroides) {
		asteroide.setXSpeed(velXAsteroides);
		asteroide.setySpeed(velYAsteroides);
	}
	
	public void buildSize() {
		asteroide.setSize(20 + random.nextInt(10));
	}
	
	public void buildPosicion() {
		asteroide.setX(random.nextInt((int)Gdx.graphics.getWidth()));
		asteroide.setY(50 + random.nextInt((int)Gdx.graphics.getHeight() - 50));
	}
	
	public void buildSprite() {
		Sprite spr = new Sprite(new Texture(Gdx.files.internal("aGreyMedium4.png")));
		asteroide.setSprite(spr);
	}
	
	public Ball2 getAsteroide() {
		return asteroide;
	}

}

//random.nextInt((int)Gdx.graphics.getWidth()) POSICION X ASTEROIDE
//50 + random.nextInt((int)Gdx.graphics.getHeight() - 50) POSICION Y ASTEROIDE