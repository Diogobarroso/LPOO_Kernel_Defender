package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Cursor {
    private float posx;
    private float posy;
    private Texture texture;
    public Sprite sprite;

    public Cursor() {
        texture = new Texture("icons/windowicon.png");
        sprite = new Sprite(texture);
    }

    public void Update() {
        posx = Gdx.input.getX();
        posy = 720 - Gdx.input.getY();
        sprite.setPosition(posx - sprite.getWidth() / 2, posy - sprite.getHeight() / 2);
    }
}
