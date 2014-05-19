package com.kerneldefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
    private SpriteBatch batch;
    private WorldController worldController;
    private BitmapFont fpstext;
    private Integer fps;

    public WorldRenderer(WorldController wc) {
        worldController = wc;
        Init();
    }

    public void Init() {
        batch = new SpriteBatch();
        fps = new Integer(Gdx.graphics.getFramesPerSecond());
        fpstext = new BitmapFont();
        fpstext.setColor(0.0f, 1.0f, 1.0f, 1.0f);
    }

    public void render() {
        fps = Gdx.graphics.getFramesPerSecond();

        batch.enableBlending(); //for transparent png's TODO: maybe this only needs to be used once?
        batch.begin();
            worldController.bg.draw(batch);
            worldController.player.sprite.draw(batch); //draw player
            for(Projectile projectile : worldController.player.projectiles) //draw projectiles
                projectile.sprite.draw(batch);
            for(Missile missile : worldController.player.missiles)
                missile.sprite.draw(batch);
            for(Enemy enemy : worldController.enemies) //draw enemies
                enemy.sprite.draw(batch);
            for(FreeFallObject banana : worldController.bananas) //draw bananas
                banana.sprite.draw(batch);
        batch.end();

        //need separate drawing method for health bars, since they're basic shapes
        for(Enemy enemy : worldController.enemies)
            enemy.healthBar.Draw(5.0f, enemy.health, batch);

        worldController.kernel.Draw(); //draw Kernel

        batch.begin();
            fpstext.draw(batch, fps.toString(), 1240.0f, 710.0f);
        batch.end();
    }
}
