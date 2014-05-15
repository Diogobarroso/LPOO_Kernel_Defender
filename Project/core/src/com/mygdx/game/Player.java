package com.mygdx.game;

import java.lang.Math;
import java.util.Iterator;
import java.util.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    public Sprite sprite;
    public Vector<Projectile> projectiles;
    public Vector<Missile> missiles;
    private Texture texture;
    //position data
    private float posx;
    private float posy;
    //angle from kernel
    private float kernelAngle;
    private float kernelDistance;
    //shooting frequency
    private float shootingFreq; //times per sec
    private float shootingTimer;
    private Sound shootingSound;

    public Player() {
        //sprite loading
        texture = new Texture("sprites/ship.png");
        sprite = new Sprite(texture);
        //set origin in center for correct scaling and rotation
        sprite.setOriginCenter();
        sprite.setScale(2.0f);

        //set initial position
        kernelAngle = -45.0f;
        kernelDistance = 150.0f;
        //we subtract half the original sprite's dimensions to place its center in the correct position
        posx = kernelDistance * (float) Math.cos(Math.toRadians(kernelAngle)) - sprite.getWidth() / 2;
        //TODO: set this position relative to window vertical size
        posy = 720 - kernelDistance * -1 * (float) Math.sin(Math.toRadians(kernelAngle)) - sprite.getHeight() / 2;
        sprite.setPosition(posx, posy) ;

        //prepare projectile container
        projectiles = new Vector<Projectile>();

        //set shooting Freq
        shootingFreq = 5.0f;
        shootingTimer = 0.0f;

        //prepare missile container
        missiles = new Vector<Missile>();

        //set SFX
        shootingSound = Gdx.audio.newSound(Gdx.files.internal("sfx/Laser_Shoot2.wav"));
    }

    public void Update() {
        UpdateOrientation();
        UpdateProjectiles();
    }

    private void UpdateOrientation() {
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

        posx = kernelDistance * (float) Math.cos(Math.toRadians(kernelAngle)) - sprite.getWidth() / 2;
        posy = 720 - kernelDistance * -1 * (float) Math.sin(Math.toRadians(kernelAngle)) - sprite.getHeight() / 2;
        sprite.setPosition(posx, posy);
    }

    public void Shoot() {
        shootingTimer += Gdx.graphics.getDeltaTime();
        if(shootingTimer >= 1 / shootingFreq) {
            projectiles.add(new Projectile(posx + sprite.getWidth() / 2, posy + sprite.getHeight() / 2, sprite.getRotation() + 90.0f));
            shootingSound.play();
            shootingTimer -= 1 / shootingFreq;
        }
    }

    public void ShootMissiles() {
        missiles.add(new Missile(posx, posy, kernelAngle + 90.0f));
    }

    private void UpdateProjectiles() {/*
        for(Projectile p : projectiles)
            p.Move();
            */
        Iterator<Projectile> iter = projectiles.iterator();

        while(iter.hasNext()) {
            Projectile projectile = iter.next();
            projectile.Move();
            if(projectile.posx > 1280.0f || projectile.posx < 0.0f || projectile.posy > 720.0f || projectile.posy < 0.0f)
                iter.remove();
        }
    }
}
