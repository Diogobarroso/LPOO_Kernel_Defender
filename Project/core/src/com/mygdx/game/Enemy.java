package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy {
    public Sprite sprite;
    private Texture texture;
    private float posx;
    private float posy;
    private float speed;
    private float angle;

    public Enemy() {
        texture = new Texture("sprites/virus.png");
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
        sprite.setScale(2.0f);

        boolean side = (Math.random() < 0.5);

        if(side) {
            posx = 1300;
            posy = (float)Math.random() * 720;
        } else {
            posx = (float)Math.random() * 1280;
            posy = -20;
        }

        sprite.setPosition(posx, posy);

        speed = 40.0f;

        float deltax = posx;
        float deltay = posy - 720;
        float distance = (float)Math.sqrt(deltax * deltax + deltay * deltay);
        angle = 180.0f - (float)Math.toDegrees(Math.asin(deltay / distance));
        if(deltay < 0)
            angle = -angle;
        System.out.println(angle);
    }

    public void Move() {
        posx += Math.cos(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        posy += Math.sin(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        sprite.setPosition(posx, posy);
    }

    public boolean CheckDestiny() {
        float deltax = posx;
        float deltay = posy - 720;
        return ((float)Math.sqrt(deltax * deltax + deltay * deltay) < 100.0f);
    }
}
