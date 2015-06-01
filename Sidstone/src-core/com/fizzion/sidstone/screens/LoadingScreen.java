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
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.rect(32, app.cam.viewportHeight / 8 - 8, app.cam.viewportWidth - 64, 8);
		sr.setColor(Color.BLUE);
		sr.rect(33, app.cam.viewportHeight / 8 - 7, progress * (app.cam.viewportWidth - 64) - 2, 6);
		sr.end();
		
		app.batch.begin();
		app.font.draw(app.batch, "LOADING...", 20, 20);
		app.batch.end();
	}
	
	private void update(float delta) {
		float lastProgress = progress;
		progress = MathUtils.lerp(progress, app.assets.getProgress(), .1f);
		if(app.assets.update() && progress - lastProgress < 0.00001) {
			app.setScreen(app.splashScreen);
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
		sr.dispose();
	}

}
