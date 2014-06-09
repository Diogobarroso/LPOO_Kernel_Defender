package GameObjects;

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

    public float damage;
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
        speed = 400.0f;
        rotSpeed = 360.0f;

        //set damage
        damage = 3.0f;

        //set target
        target = enemy;
    }

    public void Move() {
        float deltax = target.posx + target.sprite.getWidth() / 2 - posx;
        float deltay = target.posy + target.sprite.getHeight() / 2 - posy;
        float distance = (float)Math.sqrt((target.posx - posx) * (target.posx - posx) + (target.posy - posy) * (target.posy - posy));
        targetAngle = (float)Math.toDegrees(Math.acos(deltax / distance));
        if(deltay < 0)
            targetAngle = 360 - targetAngle;
        if(Math.abs(targetAngle - angle) > 5.0f) {
            if((targetAngle - angle > 0 && targetAngle - angle < 180) || (targetAngle - angle < -180 && targetAngle - angle > -360))
                angle += (rotSpeed * Gdx.graphics.getDeltaTime());
            else if((targetAngle - angle < 0 && targetAngle - angle > -180) || (targetAngle - angle < 360 && targetAngle - angle > 180))
                angle -= (rotSpeed * Gdx.graphics.getDeltaTime());
            if(angle > 360)
                angle %= 360; //to avoid overflow
            if(angle < 0)
                angle += 360.0f;
            sprite.setRotation(angle);
        }
        posx += Math.cos(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        posy += Math.sin(Math.toRadians(angle)) * speed * Gdx.graphics.getDeltaTime();
        sprite.setPosition(posx, posy);
    }

    public boolean CheckNullTarget() {
        return target == null;
    }
}
