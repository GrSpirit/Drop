package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Drop;

/**
 * Created by vita on 12.11.14.
 */
public class Cheese implements Disposable {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rectangle position;

    public Cheese(int x, int y) {
        position = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void drop() {
        position.y -= 200 * Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, position.x, position.y);
    }

    public Rectangle getPosition() {
        return position;
    }

    public boolean isFallen() {
        return position.y <= 0;
    }

    @Override
    public void dispose() {

    }
}
