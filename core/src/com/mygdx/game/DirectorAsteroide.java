package com.mygdx.game;

public class DirectorAsteroide { //Clase directora.
	private BuilderAsteroide builderAsteroide;
	
	public void setBuilderAsteroide (BuilderAsteroide builderAsteroide) {
		this.builderAsteroide = builderAsteroide;
	}
	
	public Ball2 construirAsteroide() {
		builderAsteroide.buildVelocidad();
		builderAsteroide.buildSize();
		builderAsteroide.buildPosicion();
		return builderAsteroide.getAsteroide();
	}

}
