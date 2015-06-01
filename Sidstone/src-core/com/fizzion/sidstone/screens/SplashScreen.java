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
	
	public SplashScreen(final Application app) {
		this.app = app;
		this.stage = new Stage(new FitViewport(Application.V_WIDTH, Application.V_HEIGHT, app.cam));
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		Texture splashTex = app.assets.get("img/splash.png", Texture.class);
		splashImg = new Image(splashTex);
		splashImg.setOrigin(splashImg.getWidth() / 2, splashImg.getHeight() / 2);
		
		Texture nameTex = app.assets.get("img/name.png", Texture.class);
		nameImg = new Image(nameTex);
		nameImg.setOrigin(nameImg.getWidth() / 2, nameImg.getWidth() / 2);
		
		stage.addActor(splashImg);
		stage.addActor(nameImg);
		
		splashImg.setPosition(stage.getWidth() / 2 - 16, stage.getHeight() / 2 + 100);
		nameImg.setPosition(stage.getWidth() / 2 - 54, stage.getHeight() / 2);
		
		nameImg.addAction(sequence(alpha(0), scaleTo(3f, 3f), delay(2.5f), fadeIn(.5f), delay(1f), fadeOut(1.25f)));
		splashImg.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
						parallel(fadeIn(2f, Interpolation.pow2),
								scaleTo(4f, 4f, 2.5f, Interpolation.pow5),
								moveTo(stage.getWidth() / 2 - 20, stage.getHeight() / 2 + 32, 2f, Interpolation.swing)),
								delay(1.5f), fadeOut(1.25f)));
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		stage.draw();
		
		app.batch.begin();
		app.batch.end();
	}
	
	private void update(float delta) {
		stage.act(delta);
		
		if(splashImg.getActions().size == 0)
			app.setScreen(app.mainMenuScreen);
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
