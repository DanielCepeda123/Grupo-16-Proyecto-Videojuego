package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CampoAsteroides {
    private static CampoAsteroides singleInstance = null; // Instancia única

    private ArrayList<Ball2> balls1 = new ArrayList<>();
    private ArrayList<Ball2> balls2 = new ArrayList<>();
    private int cantAsteroides;
    private int velXAsteroides; 
    private int velYAsteroides;
    
    private DirectorAsteroide directorAsteroide;
    private Ball2 bb;
    private BuilderAsteroide asteroideSmall;
    private BuilderAsteroide asteroideMedium;
    private BuilderAsteroide asteroideLarge;

    // Constructor privado
    CampoAsteroides(int cantAsteroides, int velXAsteroides, int velYAsteroides) {
        this.cantAsteroides = cantAsteroides;
        this.velXAsteroides = velXAsteroides;
        this.velYAsteroides = velYAsteroides;

        Random random = new Random();
        for (int i = 0; i < cantAsteroides; i++) {
        	directorAsteroide = new DirectorAsteroide();
        	asteroideSmall = new AsteroideSmall();
        	asteroideMedium = new AsteroideMedium();
        	asteroideLarge = new AsteroideLarge();
        	
        	if((i % 2 == 0) && (i % 5 == 0)) {
        		directorAsteroide.setBuilderAsteroide(asteroideLarge);
        		bb = directorAsteroide.construirAsteroide(velXAsteroides, velYAsteroides);
        	}else {
        		if (i % 2 == 0) {
        		directorAsteroide.setBuilderAsteroide(asteroideMedium);
        		bb = directorAsteroide.construirAsteroide(velXAsteroides, velYAsteroides);
        		}else {
        			directorAsteroide.setBuilderAsteroide(asteroideSmall);
            		bb = directorAsteroide.construirAsteroide(velXAsteroides, velYAsteroides);
        		}
        	}         
            balls1.add(bb);
            balls2.add(bb);
        }
    }

    // Método estático para obtener la instancia única
    public static CampoAsteroides getInstance(int cantAsteroides, int velXAsteroides, int velYAsteroides) {
        if (singleInstance == null) {
            singleInstance = new CampoAsteroides(cantAsteroides, velXAsteroides, velYAsteroides);
        }
        return singleInstance;
    }

    public ArrayList<Ball2> getBalls1() {
        return balls1;
    }

    public ArrayList<Ball2> getBalls2() {
        return balls2;
    }

    public int getBalls1Size() {
        return balls1.size();
    }

    public int getBalls2Size() {
        return balls2.size();
    }

    public Ball2 getAsteroide1(int j) {
        return balls1.get(j);
    }

    public Ball2 getAsteroide2(int j) {
        return balls2.get(j);
    }

    public void removerAsteroide(int j) {
        balls1.remove(j);
        balls2.remove(j);
    }

    public void actualizarAsteroides() {
        for (Ball2 ball : getBalls1()) {
            ball.update();
        }
    }
}
