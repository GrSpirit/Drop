package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Drop;

import java.util.Iterator;

/**
 * Created by vita on 12.11.14.
 */
public class CheeseFactory implements Disposable {
    public static final int SPAWN_PERIOD = 1000000000;
    private long lastSpawnTime;
    private long startSpawnTime;
    private long spawnSpeed;
    private Array<Cheese> rain;
    private Texture texture;
    private Sound dropSound;

    public CheeseFactory(FileHandle file) {
        texture = new Texture(file);
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rain = new Array<Cheese>();
        startSpawnTime = TimeUtils.nanoTime();
        spawnSpeed = SPAWN_PERIOD;
        makeCheese();
    }

    public void makeCheese() {
        lastSpawnTime = TimeUtils.nanoTime();
        rain.add(new Cheese(MathUtils.random(0, Drop.WIDTH - Cheese.WIDTH), Drop.HEIGHT));
    }

    public void spawnCheese() {
        spawnSpeed = SPAWN_PERIOD;
        if (TimeUtils.nanoTime() - lastSpawnTime > spawnSpeed) {
            makeCheese();
        }
    }

    public Iterator<Cheese> iterator() {
        return rain.iterator();
    }

    public Texture getTexture() {
        return texture;
    }

    public void draw(SpriteBatch batch) {
        for (Cheese cheese: rain) {
            cheese.draw(batch, getTexture());
        }
    }

    public Sound getDropSound() {
        return dropSound;
    }

    @Override
    public void dispose() {
        dropSound.dispose();
        texture.dispose();
    }
}
