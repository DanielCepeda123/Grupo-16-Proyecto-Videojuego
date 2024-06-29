package com.mygdx.game;

public class DirectorAsteroide { //Clase directora.
	private BuilderAsteroide builderAsteroide;
	
	public void setBuilderAsteroide (BuilderAsteroide builderAsteroide) {
		this.builderAsteroide = builderAsteroide;
	}
	
	public Ball2 construirAsteroide(int velXAsteroides, int velYAsteroides) {
		builderAsteroide.buildVelocidad(velXAsteroides, velYAsteroides);
		builderAsteroide.buildSize();
		builderAsteroide.buildPosicion();
		builderAsteroide.buildSprite();
		return builderAsteroide.getAsteroide();
	}

}
