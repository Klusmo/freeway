package com.freeway.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.freeway.game.FreeWay;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setIdleFPS(60);
		config.useVsync(true);
		config.setWindowedMode(960, 640);

		new Lwjgl3Application(new FreeWay(), config);
	}
}
