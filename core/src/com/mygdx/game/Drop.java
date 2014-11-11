package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop extends Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();

        this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
