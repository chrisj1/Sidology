package com.fizzion.sidstone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fizzion.sidstone.screens.LoadingScreen;
import com.fizzion.sidstone.screens.MainMenuScreen;
import com.fizzion.sidstone.screens.SplashScreen;

public class Application extends Game {
	
	//Window parameters
	public static final String TITLE = "Sidstone";
	public static final String DEV = "Pre-Alpha Dev Build";
	public static final float VERSION = 0.1f;
	public static final int V_WIDTH = 1600;
	public static final int V_HEIGHT = 900;
	
	//Rendering to screen components
	public OrthographicCamera cam;
	public SpriteBatch batch;
	public BitmapFont font;
	
	//Stores all assets
	public AssetManager assets;
	
	/**
	 * The collection of screens (game  states) which comprise the game.
	 * Can be accessed anywhere using a final App instance. 
	 */
	public LoadingScreen loadingScreen;
	public SplashScreen splashScreen;
	public MainMenuScreen mainMenuScreen;
	
	/**
	 * Called upon creating an instance of application (running the program).
	 */
	@Override
	public void create () {
		assets = new AssetManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		
		this.setScreen(loadingScreen);
	}

	/**
	 * Called constantly by ApplicationAdapter/Game superclass to render to screen.
	 */
	@Override
	public void render () {
		super.render();
	}
	
	/**
	 * Properly removes components from memory. 
	 * IT IS IMPERATIVE THAT YOU DISPOSE THINGS PROPERLY.
	 */
	@Override
	public void dispose()
	{
		batch.dispose();
		font.dispose();
		assets.dispose();
		loadingScreen.dispose();
		splashScreen.dispose();
		mainMenuScreen.dispose();
	}
}
