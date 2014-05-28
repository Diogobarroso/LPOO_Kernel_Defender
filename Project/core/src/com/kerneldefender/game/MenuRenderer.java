package com.kerneldefender.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuRenderer {
    private SpriteBatch batch;
    private MenuController menuController;

    public MenuRenderer(MenuController mc) {
        menuController = mc;
        Init();
    }

    public void Init() {
        batch = new SpriteBatch();
        batch.enableBlending();
    }

    public void Render() {
        batch.begin();
            menuController.bg.draw(batch);
            menuController.ship.draw(batch);
            menuController.play.draw(batch);
            menuController.options.draw(batch);
            menuController.credits.draw(batch);
            menuController.exit.draw(batch);
            menuController.cursor.sprite.draw(batch);
        batch.end();
    }
}
