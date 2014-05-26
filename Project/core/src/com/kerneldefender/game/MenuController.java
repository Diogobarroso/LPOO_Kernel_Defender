package com.kerneldefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MenuController extends InputAdapter {
    Texture bg_text;
    Sprite bg;
    Texture ship_text;
    Sprite ship;
    float rot;
    float shipRotSpeed;

    public MenuController() {
        Init();
    }

    public void Init() {
        //load background
        bg_text = new Texture("sprites/testbackground.png");
        bg = new Sprite(bg_text);

        //load rotating ship
        ship_text = new Texture("sprites/ship.png");
        ship = new Sprite(ship_text);
        ship.setOriginCenter();
        ship.setScale(25.0f);
        ship.setPosition(350.0f, 250.0f);
        rot = 0.0f;
        ship.setRotation(rot);
        shipRotSpeed = 22.5f;
    }

    public void Update() {
        rot += shipRotSpeed * Gdx.graphics.getDeltaTime();
        ship.setRotation(rot);
    }
}
