package com.freeway.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freeway.game.screens.GameScreen;

public class FreeWay extends Game {
	public static final int V_WIDTH = 288; // Largura de Road.png
	public static final int V_HEIGHT = 200;  // Altura de Road.png

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render () {
		super.render();
	}


}
