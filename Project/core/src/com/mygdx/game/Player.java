package com.mygdx.game;

import java.lang.Math;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    public Sprite sprite;
    private Texture texture;
    private float posx;
    private float posy;

    public Player() {
        //sprite loading
        texture = new Texture("tempship.png");
        sprite = new Sprite(texture);
        //set origin in center for correct scaling and rotation
        sprite.setOriginCenter();
        sprite.setScale(2.0f);

        //set initial position
        posx = 150.0f - sprite.getWidth() / 2.0f; //we subtract half the original sprite's dimensions to place its center in the correct position
        posy = 720.0f - 150.0f - sprite.getHeight() / 2; //TODO: set this position relative to window vertical size
        sprite.setPosition(posx, posy) ;
    }

    public void UpdateOrientation() {
        //remember that mouse input has an inverted y axis (I KNOW, ITS MORONIC)
        double xdelta = Gdx.input.getX() - posx;
        double ydelta = 720 - Gdx.input.getY() - posy; //TODO: set this relative to screen vertical size
        double distance = Math.sqrt(xdelta * xdelta + ydelta * ydelta);
        double angle = Math.toDegrees(Math.acos(xdelta / distance));
        if(ydelta < 0)
            angle = -angle;
        System.out.print(angle);
        System.out.print(" ");
        System.out.print(xdelta);
        System.out.print(" ");
        System.out.print(ydelta);
        System.out.println();
        sprite.setRotation((float) angle - 90.0f); //adding 90 degrees is necessary, since the sprite was drawn facing upwards
    }
}
