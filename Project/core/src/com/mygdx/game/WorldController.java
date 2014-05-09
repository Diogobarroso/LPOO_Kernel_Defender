package com.mygdx.game;

import java.util.Iterator;
import java.util.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class WorldController extends InputAdapter{
    public Kernel kernel;
    public Player player;
    public Vector<Enemy> enemies;
    private float enemySpawnFreq;
    private float enemySpawnTimer;
    private Music music;
    private Sound enemyExplosion;

    public WorldController() {
        Init();
    }

    public void Init() {
        Gdx.input.setInputProcessor(this);
        player = new Player();
        kernel = new Kernel();
        enemySpawnFreq = 1.0f;
        enemySpawnTimer = 0.0f;
        enemies = new Vector<Enemy>();

        music = Gdx.audio.newMusic(Gdx.files.internal("music/testmusic.ogg"));
        music.setLooping(true);
        music.play();

        enemyExplosion = Gdx.audio.newSound(Gdx.files.internal("sfx/Explosion4.wav"));
    }

    public void Update() {
        //player updates
        player.Update();
        EnemyUpdate();
        EnemySpawning();
        EnemyDamage(); //deal damage before enemy success for a chance at denial
        EnemySuccess();
    }

    public void HandleInput() {
        if(Gdx.app.getType() != ApplicationType.Desktop)
            return;

        //Keyboard input
        if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.A))
            player.Move("down");
        if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.D))
            player.Move("up");

        //Mouse input
        if(Gdx.input.isButtonPressed(Buttons.LEFT))
            player.Shoot();
    }

    public void EnemyUpdate() {
        for(Enemy enemy : enemies) {
            enemy.Move();
        }
    }

    public void EnemySpawning() {
        enemySpawnTimer += Gdx.graphics.getDeltaTime();
        if(enemySpawnTimer >= 1 / enemySpawnFreq) {
            enemies.add(new Enemy());
            enemySpawnTimer -= 1 / enemySpawnFreq;
        }
    }

    public void EnemySuccess() {
        Iterator<Enemy> iter = enemies.iterator();

        while(iter.hasNext()) {
            Enemy enemy = iter.next();

            if(enemy.CheckDestiny()){
                kernel.TakeDamage(enemy.damage);
                iter.remove();
            }
        }
    }

    public boolean CheckCollision(Sprite sprt1, Sprite sprt2) {
        return sprt1.getBoundingRectangle().overlaps(sprt2.getBoundingRectangle());
    }

    public void EnemyDamage() {
        Iterator<Projectile> projIter = player.projectiles.iterator();

        while(projIter.hasNext()) {
            Projectile projectile = projIter.next();
            Iterator<Enemy> enemyIter = enemies.iterator();

            while(enemyIter.hasNext()) {
                Enemy enemy = enemyIter.next();
                //deal damage
                if(CheckCollision(projectile.sprite, enemy.sprite)) {
                    enemy.TakeDamage(projectile.damage);
                    projIter.remove();
                }
                //destroy if out of health
                if(enemy.health <= 0.0f) {
                    enemyIter.remove();
                    enemyExplosion.play();
                }
            }
        }
    }
}
