package com.kerneldefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
        sprite.setOriginCenter();

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
        elements[0].posx = -10.0f;
        elements[1].posx = 4.0f;
        elements[2].posx = 90.0f;

        speed = 20.0f;

        for(int i = 0; i < 3; i++) {
            elements[i].posy = 20.0f;
        }

        elements[0].posx = 0.0f;
        elements[1].posx = 50.0f;
        elements[2].posx = 100.0f;

        //rotation of complex and elements
        float deltax = posx;
        float deltay = posy - 720;
        float distance = (float)Math.sqrt(deltax * deltax + deltay * deltay);
        angle = 180.0f - (float)Math.toDegrees(Math.asin(deltay / distance));
        if(deltay < 0)
            angle = -angle;
        sprite.setRotation(angle - 90.0f);

        elements[0].sprite.setOrigin(55.0f, 0.0f);
        elements[0].sprite.setRotation(angle - 90.0f);
        elements[0].sprite.setOriginCenter();
        elements[0].sprite.setRotation(0.0f);

        elements[1].sprite.setOrigin(100.0f, sprite.getHeight() / 2);
        elements[1].sprite.setRotation(angle -90.0f - 45.0f);
        elements[1].sprite.setOriginCenter();
        elements[1].sprite.setRotation(0.0f);

        elements[2].sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        elements[2].sprite.setRotation(angle - 90.0f - 90.0f);
        elements[2].sprite.setOriginCenter();
        elements[2].sprite.setRotation(0.0f);
    }

    public void Draw(SpriteBatch batch) {
        sprite.draw(batch);
        for(Enemy element : elements)
            element.sprite.draw(batch);
    }

    public void Move() {
        posx += Math.cos(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        posy += Math.sin(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        for(Enemy element : elements)
            element.sprite.setPosition(posx, posy);
        sprite.setPosition(posx, posy);
    }
}
