package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.model.Cheese;
import com.mygdx.game.model.CheeseFactory;
import com.mygdx.game.model.Crow;

import java.util.Iterator;

/**
 * Created by vita on 11.11.14.
 */
public class GameScreen implements Screen {
    private final Drop game;
    private final SpriteBatch batch;
    private OrthographicCamera camera;

    private Crow crow;
    CheeseFactory cheeses;

    public GameScreen(final Drop game) {
        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);

        crow = new Crow(Gdx.files.internal("crow.png"));
        cheeses = new CheeseFactory(Gdx.files.internal("cheese.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        crow.draw(batch);
        cheeses.draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            crow.moveLeft();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            crow.moveRight();
        }

        if (Gdx.input.isTouched()) {
            Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            crow.setX((int)touchPosition.x);
        }

        cheeses.spawnCheese();

        Iterator<Cheese> iterator = cheeses.iterator();
        while (iterator.hasNext()) {
            Cheese cheese = iterator.next();
            cheese.drop();
            if (cheese.isFallen()) iterator.remove();
            if (crow.caughtCheese(cheese)) iterator.remove();
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
        cheeses.dispose();
        crow.dispose();
    }
}
