package com.fizzion.sidstone.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fizzion.sidstone.Application;

public class SplashScreen implements Screen {

	private final Application app;
	private Stage stage;
	private Image splashImg;
	private Image nameImg;
	
	private Sound startup;
	private boolean played = false;
	
	public SplashScreen(final Application app) {
		this.app = app;
		this.stage = new Stage(new FitViewport(Application.V_WIDTH, Application.V_HEIGHT, app.cam));
	}
	
	@Override
	public void show() {
		init();
		createActors();
		setActorPositions();
		runActions();
	}

	@Override
	public void render(float delta) {
		clearScreen();
		update(delta);
		stage.draw();
	}
	
	private void update(float delta) {
		stage.act(delta);
		checkScreenSwitch();
		playSoundOnTime();
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void init() {
		Gdx.input.setInputProcessor(stage);
		startup = Gdx.audio.newSound(Gdx.files.internal("aud/startup.mp3"));
	}
	
	private void createActors() {
		Texture splashTex = app.assets.get("img/splash.png", Texture.class);
		splashImg = new Image(splashTex);
		splashImg.setOrigin(splashImg.getWidth() / 2, splashImg.getHeight() / 2);
		
		Texture nameTex = app.assets.get("img/name.png", Texture.class);
		nameImg = new Image(nameTex);
		nameImg.setOrigin(nameImg.getWidth() / 2, nameImg.getWidth() / 2);
		
		stage.addActor(splashImg);
		stage.addActor(nameImg);
	}
	
	private void setActorPositions() {
		splashImg.setPosition(stage.getWidth() / 2 - 200, stage.getHeight() / 2 + 100);
		nameImg.setPosition(stage.getWidth() / 2 - 54, stage.getHeight() / 2);
	}
	
	private void runActions() {
		nameImg.addAction(sequence(alpha(0), scaleTo(3f, 3f), delay(2.5f), fadeIn(.5f), delay(1f), fadeOut(1.25f)));
		splashImg.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
						parallel(fadeIn(2f, Interpolation.pow2),
								scaleTo(4f, 4f, 2.5f, Interpolation.pow5),
								moveTo(stage.getWidth() / 2 - 20, stage.getHeight() / 2 + 32, 2f, Interpolation.swing)),
								delay(1.5f), fadeOut(1.25f)));
	}
	
	private void checkScreenSwitch() {
		if(splashImg.getActions().size == 0)
			app.setScreen(app.mainMenuScreen);
	}
	
	private void playSoundOnTime() {
		if(nameImg.getColor().a >= 0.01 && !played) {
			startup.play(0.5f);
			played = true;
		}
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
		startup.dispose();
		stage.dispose();
	}

}
