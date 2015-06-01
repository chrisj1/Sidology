package com.fizzion.sidstone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game extends ApplicationAdapter {
	
	private OrthographicCamera cam;
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, w, h);
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		
		//Clear screen to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public void update(float dt)
	{
		
	}
	
	@Override
	public void dispose()
	{
		
	}
}
