package com.fizzion.sidstone.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fizzion.sidstone.Application;

public class DesktopLauncher {
	
	private static final int FPS = 60;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Application.TITLE + " " + Application.DEV + " v" + Application.VERSION;
		cfg.width = Application.V_WIDTH;
		cfg.height = Application.V_HEIGHT;
		cfg.backgroundFPS = FPS;
		cfg.foregroundFPS = FPS;
		cfg.addIcon("img/icon.png", Files.FileType.Internal);
		new LwjglApplication(new Application(), cfg);
	}
	
}
