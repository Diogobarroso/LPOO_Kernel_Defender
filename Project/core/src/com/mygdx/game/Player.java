package com.mygdx.game;

import java.lang.Math;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    public Sprite sprite;
    private Texture texture;
    //position data
    private float posx;
    private float posy;
    //angle from kernel
    private float kernelAngle;
    private float kernelDistance;

    public Player() {
        //sprite loading
        texture = new Texture("sprites/tempship.png");
        sprite = new Sprite(texture);
        //set origin in center for correct scaling and rotation
        sprite.setOriginCenter();
        sprite.setScale(2.0f);

        //set initial position
        kernelAngle = -45.0f;
        kernelDistance = (float) Math.sqrt(150.0f * 150.0f);
        //we subtract half the original sprite's dimensions to place its center in the correct position
        posx = 150 * (float) Math.cos(Math.toRadians(kernelAngle)) - sprite.getWidth() / 2;
        //TODO: set this position relative to window vertical size
        posy = 720 - 150 * -1 * (float) Math.sin(Math.toRadians(kernelAngle)) - sprite.getHeight() / 2;
        sprite.setPosition(posx, posy) ;
    }

    public void UpdateOrientation() {
        //remember that mouse input has an inverted y axis (I KNOW, ITS MORONIC)
        double xdelta = Gdx.input.getX() - (posx + sprite.getWidth() / 2);
        double ydelta = 720 - Gdx.input.getY() - (posy + sprite.getHeight() / 2); //TODO: set this relative to screen vertical size
        double distance = Math.sqrt(xdelta * xdelta + ydelta * ydelta);
        double angle = Math.toDegrees(Math.acos(xdelta / distance));
        if(ydelta < 0)
            angle = -angle;
        sprite.setRotation((float) angle - 90.0f); //adding 90 degrees is necessary, since the sprite was drawn facing upwards
    }

    public void Move(String direction) {
        if(direction.equals("up")) {
            kernelAngle += 90.0f * Gdx.graphics.getDeltaTime();
            if(kernelAngle > 0)
                kernelAngle = 0.0f;
        }
        if(direction.equals("down")) {
            kernelAngle -= 90.0f * Gdx.graphics.getDeltaTime();
            if(kernelAngle < -90.0f)
                kernelAngle = -90.0f;
        }

        posx = 150 * (float) Math.cos(Math.toRadians(kernelAngle)) - sprite.getWidth() / 2;
        posy = 720 - 150 * -1 * (float) Math.sin(Math.toRadians(kernelAngle)) - sprite.getHeight() / 2;
        sprite.setPosition(posx, posy);
    }
}
