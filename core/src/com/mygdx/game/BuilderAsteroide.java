package com.mygdx.game;

interface  BuilderAsteroide {
	
	void buildVelocidad(int velXAsteroides, int velYAsteroides);
	void buildSize();
	void buildPosicion();
	void buildSprite();
	Ball2 getAsteroide();

}
