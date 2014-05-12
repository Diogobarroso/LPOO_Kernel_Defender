package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Missile {
    public Sprite sprite;
    private Texture texture;
    private float posx;
    private float posy;
    private float speed;
    private float angle;

    public Missile(float x, float y, float alpha) {
        texture = new Texture("sprites/missile.png");
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
        sprite.setScale(2.0f);
        sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
        sprite.setRotation(alpha);
    }
}
