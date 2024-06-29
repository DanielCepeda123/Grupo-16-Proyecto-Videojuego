package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AsteroideSmall implements BuilderAsteroide{
	private Ball2 asteroide;
	private Random random = new Random();

	
	public AsteroideSmall() {
		this.asteroide = new Ball2();
	}
	
	public void buildVelocidad(int velXAsteroides, int velYAsteroides) {
		asteroide.setXSpeed(velXAsteroides * 3);
		asteroide.setySpeed(velYAsteroides * 3);
	}
	
	public void buildSize() {
		asteroide.setSize(10 + random.nextInt(5));
	}
	
	public void buildPosicion() {
		asteroide.setX(random.nextInt((int)Gdx.graphics.getWidth()));
		asteroide.setY(50 + random.nextInt((int)Gdx.graphics.getHeight() - 50));
	}
	
	public void buildSprite() {
		Texture texture = new Texture(Gdx.files.internal("aGreySmall.png"));
		Sprite spr = new Sprite(texture);
		spr.setSize(texture.getWidth(), texture.getHeight());
		asteroide.setSprite(spr);
	}
	
	public Ball2 getAsteroide() {
		return asteroide;
	}
}
