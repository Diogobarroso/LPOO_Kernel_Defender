package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Application.ApplicationType;

public class WorldController extends InputAdapter{
    public Player player;

    public WorldController() {
        Init();
    }

    public void Init() {
        Gdx.input.setInputProcessor(this);
        player = new Player();
    }

    public void Update() {
        //player updates
        player.Update();
    }

    public void HandleInput() {
        if(Gdx.app.getType() != ApplicationType.Desktop)
            return;

        //Keyboard input
        if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.A))
            player.Move("down");
        if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.D))
            player.Move("up");

        //Mouse input
        if(Gdx.input.isButtonPressed(Buttons.LEFT))
            player.Shoot();
    }
}
