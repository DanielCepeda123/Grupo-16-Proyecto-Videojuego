package com.mygdx.game;

import java.util.ArrayList;
//import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PantallaJuego extends PantallaAbstracta {

	//private SpaceNavigation game; //objeto game de clase SpaceNavigation
	//private OrthographicCamera camera;	
	private SpriteBatch batch;
	private int score; //puntaje
	private int ronda; //cont de rondas
	private int velXAsteroides; 
	private int velYAsteroides; 
	private int cantAsteroides;
	private CoordinadorSonidos sonidos;
	private CampoAsteroides campoAsteroides;
	private ManejoColisiones colisiones;
	
	private Nave4 nave;
	//private  ArrayList<Ball2> balls1 = new ArrayList<>();
	//private  ArrayList<Ball2> balls2 = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();

	private Sound soundBala = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
    private Texture txBala = new Texture(Gdx.files.internal("Rocket2.png"));
    private Sprite sprNave = new Sprite(new Texture(Gdx.files.internal("MainShip3.png")));
    private PantallaJuego juego = this;
	
	private StrategyDisparo strategyDisparo = new DisparoLento(soundBala, txBala, sprNave, juego);
	private boolean disparoLentoBool = true;
	
	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
			int velXAsteroides, int velYAsteroides, int cantAsteroides) {
		super(game);
		this.game = game; //Estaba comentado, no se por que. no parece cambiar nada
		this.ronda = ronda;
		this.score = score;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		/*camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);*/
		//inicializar assets; musica de fondo y efectos de sonido
		sonidos = new CoordinadorSonidos();
		
	    // cargar imagen de la nave, 64x64   
	    nave = new Nave4(Gdx.graphics.getWidth()/2-50,30, sprNave/*new Texture(Gdx.files.internal("MainShip3.png"))*/,
	    				Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), 
	    				txBala, 
	    				soundBala, strategyDisparo); 
        nave.setVidas(vidas);
        //crear asteroides
        campoAsteroides = new CampoAsteroides(cantAsteroides, velXAsteroides, velYAsteroides);
        colisiones = new ManejoColisiones(campoAsteroides, balas, sonidos, score, nave, batch);
	}
	
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	
	@Override
	public void render(float delta) {
		
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		  
          batch.begin();
          
		  dibujaEncabezado();
		  
	      if (!nave.estaHerido()) {
		      // colisiones entre balas y asteroides y su destruccion 
	    	  score = colisiones.colisionBalaAsteroide();
	    	  
		      //actualizar movimiento de asteroides dentro del area
	    	  campoAsteroides.actualizarAsteroides();
		      //colisiones entre asteroides y sus rebotes
		      colisiones.colisionAsteroides(); 
		     
		      if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
		            if (disparoLentoBool) {
		                strategyDisparo = new DisparoRapido(soundBala, txBala, sprNave, this);
		            } else {
		                strategyDisparo = new DisparoLento(soundBala, txBala, sprNave, this);
		            }
		            disparoLentoBool = !disparoLentoBool; // Cambiar el booleano de estrategia
		            nave.setStrategyDisparo(strategyDisparo); // Actualizar la estrategia en la nave
		        }
		      
	      }
	      
	      //dibujar balas
	     for (Bullet bala : balas) {       
	          bala.draw(batch);
	      }
	     
	      nave.draw(batch, this);
	      
	      //dibujar asteroides y manejar colision con nave
	      colisiones.colisionNaveAsteroide();
	      
	      if(Gdx.input.isKeyJustPressed(Input.Keys.D) && disparoLentoBool == false) strategyDisparo = new DisparoLento(soundBala, txBala, sprNave, this);
	      
	      if (nave.estaDestruido()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      
	      batch.end();
	      
	      //nivel completado
	      if (campoAsteroides.getBalls1Size()==0) {
			Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score, 
					velXAsteroides+3, velYAsteroides+3, cantAsteroides+10);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		  }
	    	 
	}
    
    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		sonidos.playGameMusic();
	}

	@Override
	public void dispose() {
		sonidos.dispose();
		// TODO Auto-generated method stub
	}
   
}
