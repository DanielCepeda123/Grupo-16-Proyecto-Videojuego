package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class PantallaAbstracta implements Screen {
	protected SpaceNavigation game;
	protected OrthographicCamera camera;
	
	public PantallaAbstracta(SpaceNavigation game) {
		this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1200, 800);
	}
	
	public abstract void render(float delta);
	
	public abstract void show();
	
	public abstract void dispose();
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
}
