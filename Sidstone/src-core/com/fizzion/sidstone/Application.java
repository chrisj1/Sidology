package com.fizzion.sidstone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.fizzion.sidstone.screens.HelpScreen;
import com.fizzion.sidstone.screens.LoadingScreen;
import com.fizzion.sidstone.screens.MainMenuScreen;
import com.fizzion.sidstone.screens.SplashScreen;

public class Application extends Game {
	
	//Window parameters
	public static final String TITLE = "Sidstone";
	public static final String DEV = "Pre-Alpha Dev Build";
	public static final float VERSION = 0.1f;
	public static int V_WIDTH = 860;
	public static int V_HEIGHT = 540;
	
	//Rendering to screen components
	public OrthographicCamera cam;
	public SpriteBatch batch;
	public BitmapFont font24;
	public BitmapFont font108;
	
	//Stores all assets
	public AssetManager assets;
	
	/**
	 * The collection of screens (game  states) which comprise the game.
	 * Can be accessed anywhere using a final App instance. 
	 */
	public LoadingScreen loadingScreen;
	public SplashScreen splashScreen;
	public MainMenuScreen mainMenuScreen;
	public HelpScreen helpScreen;
	
	/**
	 * Called upon creating an instance of application (running the program).
	 */
	@Override
	public void create () {
		assets = new AssetManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		batch = new SpriteBatch();
		initFonts();
		
		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		helpScreen = new HelpScreen(this);
		
		this.setScreen(loadingScreen);
	}

	/**
	 * Called constantly by ApplicationAdapter/Game superclass to render to screen.
	 */
	@Override
	public void render () {
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
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
		font24.dispose();
		font108.dispose();
		assets.dispose();
		loadingScreen.dispose();
		splashScreen.dispose();
		mainMenuScreen.dispose();
		helpScreen.dispose();
	}
	
	private void initFonts() {
		FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("font/ComicNeue.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		
		params.size = 24;
		params.color = Color.BLUE;
		font24 = g.generateFont(params);
		params.size = 108;
		params.color = Color.GREEN;
		font108 = g.generateFont(params);
	}
}
