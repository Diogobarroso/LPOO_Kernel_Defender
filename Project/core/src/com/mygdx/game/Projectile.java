package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Projectile {
    public Sprite sprite;
    private Texture texture;

    public Projectile() {
        //sprite loading
        texture = new Texture("projectile.png");
        sprite = new Sprite(texture);
        //set origin in center
        sprite.setOriginCenter();
        sprite.setScale(2.0f);
    }
}
