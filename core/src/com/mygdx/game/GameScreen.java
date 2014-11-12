package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Crow;

/**
 * Created by vita on 11.11.14.
 */
public class GameScreen implements Screen {
    private final Drop game;
    private final SpriteBatch batch;
    private OrthographicCamera camera;

    private Crow crow;

    public GameScreen(final Drop game) {
        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);

        crow = new Crow(Gdx.files.internal("crow.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        crow.draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            crow.moveLeft();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            crow.moveRight();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
