package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class CoordinadorSonidos {
	
	private Sound explosionSound;
	private Music gameMusic;

	public CoordinadorSonidos() {
		//El constructor inicializa la musica de fondo y las explosiones.
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); 
		
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);
        gameMusic.play();
	}
	
	public void dispose() {
        explosionSound.dispose();
        gameMusic.dispose();
    }
	
	public void playGameMusic() {
		gameMusic.play();
	}
	
	public void playExplosionSound() {
		explosionSound.play();
	}
}

