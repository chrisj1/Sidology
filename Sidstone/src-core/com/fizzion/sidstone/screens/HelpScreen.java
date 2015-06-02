package com.fizzion.sidstone.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.fizzion.sidstone.Application;

public class HelpScreen implements Screen {

	private final Application app;
	
	public HelpScreen(final Application app) {
		this.app = app;
	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		clearScreen();
		update(delta);
		drawText();
	}
	
	private void update(float delta) {
		
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void drawText() {
		app.batch.begin();
		app.font108.draw(app.batch, "DEBUG HELP SCREEN", Application.V_WIDTH / 2 - 500, Application.V_HEIGHT / 2 + 100);
		app.batch.end();
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
