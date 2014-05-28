package com.kerneldefender.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class KernelDefender extends ApplicationAdapter {
    public enum Mode {MENU, PLAY};
    static Mode currentMode;
    public MenuController menuController;
    public MenuRenderer menuRenderer;
    public WorldController worldController;
    public WorldRenderer worldRenderer;

    @Override
    public void create () {
        Gdx.input.setCursorCatched(true);

        currentMode = Mode.MENU;

        menuController = new MenuController();
        menuRenderer = new MenuRenderer(menuController);
        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);

        //initial game mode
        menuController.Init();
    }

    @Override
    public void render () {
        //main game loop

        switch(currentMode) {
            case MENU:
                //general updates
                menuController.Update(this);
                //drawing
                //clear screen
                Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                //render game objects
                menuRenderer.Render();
                break;
            case PLAY:
                //handle input
                worldController.HandleInput();
                //general updates
                worldController.Update();
                //drawing
                //clear screen
                Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                //render game objects
                worldRenderer.Render();
                break;
        }
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
