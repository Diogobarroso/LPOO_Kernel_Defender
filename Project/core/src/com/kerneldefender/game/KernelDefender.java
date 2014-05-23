package com.kerneldefender.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class KernelDefender extends ApplicationAdapter {
    private WorldController worldController;
    private WorldRenderer worldRenderer;

    @Override
    public void create () {
        Gdx.input.setCursorCatched(true);
        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);
    }

    @Override
    public void render () {
        //main game loop
        //handle input
        worldController.HandleInput();
        //general updates
        worldController.Update();
        //drawing
        //clear screen
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //render game objects
        worldRenderer.render();
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
