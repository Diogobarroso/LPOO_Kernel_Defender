package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
    private SpriteBatch batch;
    private WorldController worldController;

    public WorldRenderer(WorldController wc) {
        worldController = wc;
        Init();
    }

    public void Init() {
        batch = new SpriteBatch();
    }

    public void render() {
        batch.enableBlending(); //for transparent png's
        batch.begin();
            worldController.player.sprite.draw(batch); //draw player
            for(Projectile projectile : worldController.player.projectiles) //draw projectiles
                projectile.sprite.draw(batch);
            for(Enemy enemy : worldController.enemies) { //draw enemies
                enemy.sprite.draw(batch);
            }
        batch.end();

        //need separate drawing method for health bars, since they're basic shapes
        for(Enemy enemy : worldController.enemies)
            enemy.healthBar.Draw(5.0f, enemy.health, batch);

        worldController.kernel.Draw();
    }
}
