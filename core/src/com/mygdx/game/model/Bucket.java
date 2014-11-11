package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Drop;


/**
 * Created by vita on 11.11.14.
 */
public class Bucket implements Disposable {
    public static int WIDTH = 64;
    public static int HEIGHT = 64;
    private Rectangle position;
    private Texture texture;

    public Bucket (FileHandle file) {
        texture = new Texture(file);
        position = new Rectangle(Drop.WIDTH/2 - WIDTH/2, 20, WIDTH, HEIGHT);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getTexture(), position.x, position.y);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void setX(int x) {
        position.x = x;
    }

    public void setY(int y) {
        position.y = y;
    }

    public void moveLeft() {
        position.x -= 200 * Gdx.graphics.getDeltaTime();
        if (position.x < 0) position.x = 0;
    }

    public void moveRight() {
        position.x += 200 * Gdx.graphics.getDeltaTime();
        if (position.x > Drop.WIDTH - position.width) position.x = Drop.WIDTH - position.width;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
