package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class FreeFallObject {
    public Sprite sprite;
    private Texture texture;
    public float posx;
    public float posy;
    private float velx;
    private float vely;
    private float rot;
    private float rotVel;

    public FreeFallObject() {
        texture = new Texture("sprites/banana.png");
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
        sprite.setScale(2.0f);
        posx = 1280.0f / 2 - sprite.getWidth() / 2;
        posy = -20.0f - sprite.getHeight() / 2;
        sprite.setPosition(posx, posy);

        velx = (float) Math.random() * 600 - 300;
        vely = (float) Math.random() * 1000;

        rot = 0.0f;
        rotVel = (float) Math.random() * 720 - 360;
    }

    public void Move(){
        vely -= 600 * Gdx.graphics.getDeltaTime();
        posx += velx * Gdx.graphics.getDeltaTime();
        posy += vely * Gdx.graphics.getDeltaTime();
        sprite.setPosition(posx, posy);
    }

    public void Rotate() {
        rot += rotVel * Gdx.graphics.getDeltaTime();
        sprite.setRotation(rot);
    }
}
