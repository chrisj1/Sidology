package com.fizzion.sidstone.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.fizzion.sidstone.Application;

public class MainMenuScreen implements Screen {

	private final Application app;
	private Stage stage;
	
	public MainMenuScreen(final Application app) {
		this.app = app;
		this.stage = new Stage();
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		clearScreen();
		update(delta);
		drawDebugText();
	}
	
	private void update(float delta) {
		
	}
	
	private void drawDebugText() {
		app.batch.begin();
		app.batch.draw(app.assets.get("img/test.png", Texture.class), 0, 0);
		app.batch.end();
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(0.2f, 0f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
	}

}
