package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
        player.setPosition(640 - player.getWidth() / 2, 360 - player.getHeight() / 2);
        player.setScale(2.0f);
        player.setOrigin(player.getWidth() / 2, player.getHeight() / 2);
        player.setRotation(rotation);
	}

	@Override
	public void render () {
        rotation += rotSpeed * Gdx.graphics.getDeltaTime() % 360.0f;
        player.setRotation(rotation);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            player.draw(batch);
        batch.end();
	}
}
