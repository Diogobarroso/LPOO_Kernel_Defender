package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Projectile {
    public Sprite sprite;
    private Texture texture;
    public float posx;
    public float posy;
    private float speed;
    private float angle;

    public float damage;

    public Projectile(float x, float y, float alpha) {
        //sprite loading
        texture = new Texture("sprites/projectile.png");
        sprite = new Sprite(texture);
        //set origin in center
        sprite.setOriginCenter();
        sprite.setScale(2.0f);

        //set initial position
        posx = x;
        posy = y;
        sprite.setPosition(posx, posy);

        //set projectile speed
        speed = 1280.0f;

        //set projectile angle
        angle = alpha;
        sprite.setRotation(angle);

        //set damage
        damage = 1.0f;
    }

    public void Move() {
        posx += Math.cos(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        posy += Math.sin(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        sprite.setPosition(posx, posy);
    }
}
