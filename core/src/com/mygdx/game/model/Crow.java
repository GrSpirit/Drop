package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Drop;


/**
 * Created by vita on 11.11.14.
 */
public class Crow implements Disposable {
    public static int WIDTH = 64;
    public static int HEIGHT = 64;
    private static double JUMP_ACCELERATION = 150;
    private static double JUMP_IMPULSE = 200;
    private Rectangle position;
    private Texture texture;
    private Sound sound;

    private long jumpStartTime;
    private boolean isJumping;

    public Crow(FileHandle file) {
        texture = new Texture(file);
        sound = Gdx.audio.newSound(Gdx.files.internal("crow1.wav"));
        position = new Rectangle(Drop.WIDTH/2 - WIDTH/2, 20, WIDTH, HEIGHT);
        isJumping = false;
    }

    public void draw(SpriteBatch batch) {
        if (isJumping) {
            double pos = jumpPosition(TimeUtils.nanoTime() - jumpStartTime);
            if (pos <= 20) {
                pos = 20;
                isJumping = false;
            }
            position.y = (float)pos;
        }
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

    public boolean caughtCheese(Cheese cheese) {
        return this.position.overlaps(cheese.getPosition());
    }

    public void jump() {
        if (!isJumping) {
            this.getSound().play();
            jumpStartTime = TimeUtils.nanoTime();
            isJumping = true;
        }
    }

    private double jumpPosition(long time) {
        double dtime = (double)time / 1000000000.0;
        return 20.0 + (JUMP_IMPULSE * dtime - JUMP_ACCELERATION * dtime * dtime / 2.0);
    }

    @Override
    public void dispose() {
        sound.dispose();
        texture.dispose();
    }

    public Sound getSound() {
        return sound;
    }
}
