package com.kerneldefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ComplexEnemy {
    private Texture texture;
    private Sprite sprite;
    private Enemy[] elements;
    private float posx;
    private float posy;
    private float speed;
    private float angle;

    public ComplexEnemy() {
        texture = new Texture("sprites/malware.png");
        sprite = new Sprite(texture);

        elements = new Enemy[3];
        elements[0] = new Enemy();
        elements[1] = new Enemy();
        elements[2] = new Enemy();

        //complex enemy positioning
        boolean side = (Math.random() < 0.5);

        if(side) {
            posx = 1300;
            posy = (float)Math.random() * 720;
        } else {
            posx = (float)Math.random() * 1280;
            posy = -20;
        }

        //element positioning, relative to complex position
        elements[0].posx = posx + 10.0f;
        elements[1].posx = posx + 50.0f;
        elements[2].posx = posx + 90.0f;

        speed = 20.0f;

        for(int i = 0; i < 3; i++) {
            elements[i].posy = 50.0f;
        }

        //rotation of complex and elements
        float deltax = posx - 0;
        float deltay = posy - 720;
        angle = (float) Math.toDegrees(Math.acos((1280 * posx + 720 * posy) / (Math.sqrt(deltax * deltax + deltay * deltay) * Math.sqrt(1280 * 1280 + 0 * 0))));
        sprite.setRotation(angle - 90.0f);

        for(int i = 0; i < 3; i++) {
            elements[i].sprite.setOrigin(-10.0f, -50.0f);
        }
    }

    public void Move() {
        posx += Math.cos(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        posy += Math.sin(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        sprite.setPosition(posx, posy);
    }
}
