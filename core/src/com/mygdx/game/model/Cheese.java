package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Drop;

/**
 * Created by vita on 12.11.14.
 */
public class Cheese implements Disposable {
    public static Texture texture;
    public static int WIDTH = 64;
    public static int HEIGHT = 64;
    private Rectangle position;

    public Cheese() {
        position = new Rectangle(MathUtils.random(0, Drop.WIDTH - WIDTH), Drop.HEIGHT, WIDTH, HEIGHT);
    }

    public void drop() {
        position.y -= 200 * Gdx.graphics.getDeltaTime();
    }

    public static Cheese spawnCheese() {
        return new Cheese();
    }

    @Override
    public void dispose() {

    }
}
