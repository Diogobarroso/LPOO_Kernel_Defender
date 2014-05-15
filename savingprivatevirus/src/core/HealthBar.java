package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HealthBar {
    private float posx;
    private float posy;
    private float width;
    private float height;
    private ShapeRenderer shapeRenderer;

    public HealthBar(float x, float y, float w, float h) {
        posx = x;
        posy = y;
        width = w;
        height = h;

        shapeRenderer = new ShapeRenderer();
    }

    public void Move(float x, float y) {
        posx = x;
        posy = y;
    }

    public void Draw(float total, float current, SpriteBatch batch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1.0f - current / total, current / total, 0, 1);
        shapeRenderer.rect(posx, posy, width * (current / total), height);
        shapeRenderer.end();
    }
}
