package com.fizzion.sidstone.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
	
	public SplashScreen(final Application app) {
		this.app = app;
		this.stage = new Stage(new FitViewport(Application.V_WIDTH, Application.V_HEIGHT, app.cam));
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		Texture splashTex = app.assets.get("splash.png", Texture.class);
		splashImg = new Image(splashTex);
		splashImg.setOrigin(splashImg.getWidth() / 2, splashImg.getHeight() / 2);
		
		stage.addActor(splashImg);
		
		splashImg.setPosition(stage.getWidth() / 2 - 32, stage.getHeight() / 2 + 100);
		
		splashImg.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
						parallel(fadeIn(2f, Interpolation.pow2),
								scaleTo(2f, 2f, 2.5f, Interpolation.pow5),
								moveTo(stage.getWidth() / 2 - 32, stage.getHeight() / 2 - 26, 2f, Interpolation.swing)),
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
