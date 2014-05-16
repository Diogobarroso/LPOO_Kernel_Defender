package com.kerneldefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Missile {
    public Sprite sprite;
    private Texture texture;
    public float posx;
    public float posy;
    private float speed;
    private float rotSpeed;
    private float angle;
    private float targetAngle;
    public Enemy target;

    public Missile(float x, float y, float alpha, Enemy enemy) {
        texture = new Texture("sprites/missile.png");
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
        sprite.setScale(2.0f);
        sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
        sprite.setRotation(alpha);

        //set position
        posx = x - sprite.getWidth() / 2;
        posy = y - sprite.getHeight() / 2;

        //set angle
        angle = alpha;

        //set speeds
        speed = 100.0f;
        rotSpeed = 180.0f;

        //set target
        target = enemy;
    }

    public void Move() {
        float deltax = target.posx - posx;
        float distance = (float)Math.sqrt((target.posx - posx) * (target.posx - posx) + (target.posy - posy) * (target.posy - posy));
        targetAngle = (float)Math.toDegrees(Math.acos(deltax / distance));
        System.out.println(targetAngle + " " + angle);
        if(Math.abs(targetAngle - angle) > 40.0f) {
            angle -= (rotSpeed * Gdx.graphics.getDeltaTime());
            angle = angle % 360; //to avoid overflow
            sprite.setRotation(angle);
        }
        posx += Math.cos(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        posy += Math.sin(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        sprite.setPosition(posx, posy);
    }
}
