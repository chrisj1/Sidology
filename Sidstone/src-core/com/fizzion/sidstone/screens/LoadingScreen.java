package com.fizzion.sidstone.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.fizzion.sidstone.Application;

public class LoadingScreen implements Screen {

	private final Application app;
	
	private ShapeRenderer sr;
	
	private float progress;
	private String loading = "LOADING AWESOMENESS";
	
	public LoadingScreen(final Application app) {
		this.app = app;
		this.sr = new ShapeRenderer();
	}
	
	public void queueAssets() {
		app.assets.load("img/badlogic.jpg", Texture.class); //Example
		app.assets.load("img/splash.png", Texture.class); //Studio icon
		app.assets.load("img/name.png", Texture.class); //Studio name
	}
	
	@Override
	public void show() {
		this.progress = 0;
		queueAssets();
	}

	@Override
	public void render(float delta) {
		clearScreen();
		update(delta);		
		renderBars();
		renderLoadingText();
	}
	
	private void update(float delta) {
		updateLoadingBar();
		updateLoadingText();
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void renderBars() {
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.rect(32, app.cam.viewportHeight / 8 - 10, app.cam.viewportWidth - 64, 10);
		sr.setColor(Color.RED);
		sr.rect(33, app.cam.viewportHeight / 8 - 9, progress * (app.cam.viewportWidth - 64) - 2, 8);
		sr.end();
	}
	
	private void renderLoadingText() {
		app.batch.begin();
		app.font.draw(app.batch, loading, 20, 20);
		app.batch.end();
	}
	
	private void updateLoadingBar() {
		float lastProgress = progress;
		progress = MathUtils.lerp(progress, app.assets.getProgress(), .1f);
		if(app.assets.update() && progress - lastProgress < 0.00001) {
			app.setScreen(app.splashScreen);
		}
	}
	
	private void updateLoadingText() {
		loading = loading + ".";
		if(loading.length() > 371)
			loading = "LOADING AWESOMENESS";
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
		sr.dispose();
	}

}
