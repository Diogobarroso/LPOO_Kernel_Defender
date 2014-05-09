package com.mygdx.game;

import java.util.Iterator;
import java.util.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Music;


public class WorldController extends InputAdapter{
    public Player player;
    public Vector<Enemy> enemies;
    private float enemySpawnFreq;
    private float enemySpawnTimer;
    private Music music;

    public WorldController() {
        Init();
    }

    public void Init() {
        Gdx.input.setInputProcessor(this);
        player = new Player();
        enemySpawnFreq = 1.0f;
        enemySpawnTimer = 0.0f;
        enemies = new Vector<Enemy>();

        music = Gdx.audio.newMusic(Gdx.files.internal("music/testmusic.ogg"));
        music.setLooping(true);
        music.play();
    }

    public void Update() {
        //player updates
        player.Update();
        EnemyUpdate();
        EnemySpawning();
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
        /*
        for(Enemy enemy : enemies) {
            if(enemy.CheckDestiny())
                enemies.remove(enemy);
        }
        */
        Iterator<Enemy> iter = enemies.iterator();

        while(iter.hasNext()) {
            Enemy enemy = iter.next();

            if(enemy.CheckDestiny())
                iter.remove();
        }
    }
}
