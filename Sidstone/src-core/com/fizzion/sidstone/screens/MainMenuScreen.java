package com.fizzion.sidstone.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.fizzion.sidstone.Application;

public class MainMenuScreen implements Screen {

	private final Application app;
	private Stage stage;
	private Skin skin;
	
	private Image bgImg;
	
	private Sound menuloop;
	
	private TextButton buttonPlay;
	private TextButton buttonHelp;
	private TextButton buttonOptions;
	private TextButton buttonQuit;
	
	public MainMenuScreen(final Application app) {
		this.app = app;
		this.stage = new Stage(new ScalingViewport(Scaling.stretch, Application.V_WIDTH, Application.V_HEIGHT, app.cam));
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		menuloop = Gdx.audio.newSound(Gdx.files.internal("aud/menuloop.mp3"));
		menuloop.loop();
		
		skin = new Skin();
		skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
		skin.add("default-font", app.font24);
		skin.load(Gdx.files.internal("ui/uiskin.json"));
		
		createActors();
		initButtons();
	}

	@Override
	public void render(float delta) {
		clearScreen();
		update(delta);
		stage.draw();
		drawTitle();
	}
	
	private void update(float delta) {
		stage.act(delta);
	}
	
	private void createActors() {
		Texture bgTex = app.assets.get("img/test.png", Texture.class);
		bgImg = new Image(bgTex);
		bgImg.setPosition(0, 0);
		//bgImg.setScale(Application.V_WIDTH / bgImg.getWidth(), Application.V_HEIGHT / bgImg.getHeight());
		
		stage.addActor(bgImg);
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void drawTitle() {
		app.batch.begin();
		app.font108.draw(app.batch, "SIDSTONE", (stage.getWidth() / 2) - 235, 750);
		app.batch.end();
	}
	
	private void initButtons() {
		buttonPlay = new TextButton("Play", skin, "default");
		buttonPlay.setSize(280, 60);
		buttonPlay.setPosition((stage.getWidth() / 2) - (buttonPlay.getWidth() / 2), 400);
		buttonHelp = new TextButton("Help", skin, "default");
		buttonHelp.setSize(280, 60);
		buttonHelp.setPosition((stage.getWidth() / 2) - (buttonHelp.getWidth() / 2), 330);
		buttonOptions = new TextButton("Settings", skin, "default");
		buttonOptions.setSize(280, 60);
		buttonOptions.setPosition((stage.getWidth() / 2) - (buttonOptions.getWidth() / 2), 260);
		buttonQuit = new TextButton("Quit",skin, "default");
		buttonQuit.setSize(280, 60);
		buttonQuit.setPosition((stage.getWidth() / 2) - (buttonQuit.getWidth() / 2), 190);
		
		addClickListeners();
		
		stage.addActor(buttonPlay);
		stage.addActor(buttonHelp);
		stage.addActor(buttonOptions);
		stage.addActor(buttonQuit);
	}
	
	private void addClickListeners() {
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("PLAY");
			}
		});
		
		buttonHelp.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				app.setScreen(app.helpScreen);
			}
		});
		
		buttonOptions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("OPTIONS");
			}
		});
		
		buttonQuit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
