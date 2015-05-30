package com.fizzion.sidstone.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fizzion.sidstone.Game;

public class DesktopLauncher {
	
	private static final String TITLE = "Sidstone";
	
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;
	private static final int FPS = 60;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = TITLE;
		cfg.width = WIDTH;
		cfg.height = HEIGHT;
		cfg.backgroundFPS = FPS;
		cfg.foregroundFPS = FPS;
		new LwjglApplication(new Game(), cfg);
	}
	
}
