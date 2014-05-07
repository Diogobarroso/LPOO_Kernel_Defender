package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.*;

public class KernelDefender extends ApplicationAdapter {
	SpriteBatch batch;
    Texture playerTex;
    Sprite player;
    float rotation = 0.0f;
    float rotSpeed = 180.0f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        playerTex = new Texture("tempship.png");
        player = new Sprite(playerTex);
        player.setScale(2.0f);
        player.setOrigin(player.getWidth() / 2, player.getHeight() / 2);
        player.setRotation(rotation);
	}

	@Override
	public void render () {
        rotation += rotSpeed * Gdx.graphics.getDeltaTime() % 360.0f;
        player.setRotation(rotation);
        player.setPosition(Gdx.input.getX() - player.getWidth() / 2, 720 - Gdx.input.getY() - player.getHeight() / 2);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            player.draw(batch);
        batch.end();
	}

    @Override
    public void resize(int width, int height) {

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
