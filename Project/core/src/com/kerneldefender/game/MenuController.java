package com.kerneldefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MenuController extends InputAdapter {
    private Texture bg_text;
    public Sprite bg;
    private Texture ship_text;
    public Sprite ship;
    private float rot;
    private float shipRotSpeed;

    //buttons
    private Texture play_text;
    public Sprite play;
    private Texture options_text;
    public Sprite options;
    private Texture credits_text;
    public Sprite credits;
    private Texture exit_text;
    public Sprite exit;

    public Cursor cursor;

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

        //load buttons
        play_text = new Texture("sprites/playbutton.png");
        play = new Sprite(play_text);
        play.setPosition(900.0f, 400.0f);
        options_text = new Texture("sprites/optionsbutton.png");
        options = new Sprite(options_text);
        options.setPosition(900.0f, 300.0f);
        credits_text = new Texture("sprites/creditsbutton.png");
        credits = new Sprite(credits_text);
        credits.setPosition(900.0f, 200.0f);
        exit_text = new Texture("sprites/exitbutton.png");
        exit = new Sprite(exit_text);
        exit.setPosition(900.0f, 100.0f);
    }

    public void Update() {
        rot += shipRotSpeed * Gdx.graphics.getDeltaTime();
        ship.setRotation(rot);
    }

    //event handler
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if(button == Input.Buttons.LEFT)
            if(CheckMouseClick(cursor, play))
                ;
        return true;
    }

    private boolean CheckMouseClick(Cursor crsr, Sprite sprt) {
        return (crsr.sprite.getX() + crsr.sprite.getWidth() / 2 > sprt.getX() && crsr.sprite.getX() + crsr.sprite.getWidth() / 2 < sprt.getX() + sprt.getWidth() && crsr.sprite.getY() + crsr.sprite.getHeight() / 2 > sprt.getY() && crsr.sprite.getY() + crsr.sprite.getHeight() / 2 < sprt.getY() + sprt.getHeight());
    }
}
