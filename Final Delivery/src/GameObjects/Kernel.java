package GameObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Kernel {
    private float posx;
    private float posy;
    private ShapeRenderer shapeRenderer;
    private float health;

    public Kernel() {
        posx = 0.0f;
        posy = 720.0f;

        health = 100.0f;

        shapeRenderer = new ShapeRenderer();
    }

    public void Draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1 - health / 100.0f, health / 100.0f, 0, 1);
        shapeRenderer.circle(posx, posy, 100.0f);
        shapeRenderer.end();
    }

    public float GetHealth() {
        return health;
    }

    public void TakeDamage(float dmg) {
        health -= dmg;
    }
}
