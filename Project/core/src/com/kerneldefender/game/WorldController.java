package com.kerneldefender.game;

import java.util.Iterator;
import java.util.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WorldController extends InputAdapter{
    public Kernel kernel;
    public Player player;
    public Vector<Enemy> enemies;
    public Vector<ComplexEnemy> complexEnemies;
    public Vector<FreeFallObject> bananas;
    private Texture bg_text;
    public Sprite bg;
    private float enemySpawnFreq;
    private float enemySpawnTimer;
    private float complexEnemySpawnFreq;
    private float complexEnemySpawnTimer;
    private Music music;
    private Sound enemyExplosion;
    public Cursor cursor;

    public WorldController() {
        Init();
    }

    public void Init() {
        Gdx.input.setInputProcessor(this);
        player = new Player();
        kernel = new Kernel();
        enemySpawnFreq = 0.5f;
        enemySpawnTimer = 0.0f;
        complexEnemySpawnFreq = 0.1f;
        complexEnemySpawnTimer = 0.0f;
        enemies = new Vector<Enemy>();
        complexEnemies = new Vector<ComplexEnemy>();
        bananas = new Vector<FreeFallObject>();
        bg_text = new Texture("sprites/testbackground.png");
        bg = new Sprite(bg_text);
        cursor = new Cursor();

        music = Gdx.audio.newMusic(Gdx.files.internal("music/testmusic.ogg"));
        music.setLooping(true);
        music.play();

        enemyExplosion = Gdx.audio.newSound(Gdx.files.internal("sfx/Explosion4.wav"));
    }

    public void Update() {
        //player updates
        player.Update();
        PlayerMissileBarrage();
        PlayerMissileRetasking();

        //enemy updates
        EnemyUpdate();
        EnemySpawning();
        EnemyDamage(); //deal damage before enemy success for a chance at denial
        EnemySuccess();

        //fruity updates
        BananaUpdate();
        BananaCheckBounds();

        //cursor updates
        cursor.Update();
    }

    //continuous input handler
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

        //BANANAS
        if(Gdx.input.isKeyPressed(Keys.B))
            bananas.add(new FreeFallObject());
    }

    //event handler
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if(button == Buttons.RIGHT)
            player.DeployMissiles();
        return true;
    }

    public void PlayerMissileBarrage() {
        if(player.shootingMissiles) {
            player.missileTimer += Gdx.graphics.getDeltaTime();
            if(player.missileTimer >= 1.0f) {
                player.shootingMissiles = false;
                player.missileTimer = 0.0f;
                player.missilesFired = 0.0f;
            }

            if(player.missilesFired < player.missileShootCapacity && player.missileTimer > player.missilesFired / player.missileShootFreq) {
                Enemy closestEnemy = null;
                float bestDist = 10000.0f;
                float deltax, deltay, distance;
                for (Enemy enemy : enemies) {
                    deltax = enemy.posx - player.posx;
                    deltay = enemy.posy - player.posy;
                    distance = (float) Math.sqrt(deltax * deltax + deltay * deltay);
                    if (distance < bestDist) {
                        bestDist = distance;
                        closestEnemy = enemy;
                    }
                }
                if (closestEnemy != null)
                    player.ShootMissiles(closestEnemy);
                player.missilesFired++;
            }
        }
    }

    public void PlayerMissileRetasking() {
        Iterator<Missile> iter = player.missiles.iterator();

        while(iter.hasNext()) {
            Missile missile = iter.next();
            if(missile.posx > 1280.0f || missile.posx < 0.0f || missile.posy > 720.0f || missile.posy < 0.0f || missile.target.health <= 0.0f) {
                Enemy closestEnemy = null;
                float bestDist = 10000.0f;
                float deltax, deltay, distance;
                for (Enemy enemy : enemies) {
                    deltax = enemy.posx - player.posx;
                    deltay = enemy.posy - player.posy;
                    distance = (float) Math.sqrt(deltax * deltax + deltay * deltay);
                    if (distance < bestDist) {
                        bestDist = distance;
                        closestEnemy = enemy;
                    }
                }
                missile.target = closestEnemy;
            }
        }
    }

    public void EnemyUpdate() {
        for(Enemy enemy : enemies)
            enemy.Move();

        for(ComplexEnemy complexEnemy : complexEnemies)
            complexEnemy.Move();
    }

    public void EnemySpawning() {
        //normal enemy spawning
        enemySpawnTimer += Gdx.graphics.getDeltaTime();
        if(enemySpawnTimer >= 1 / enemySpawnFreq) {
            enemies.add(new Enemy());
            enemySpawnTimer -= 1 / enemySpawnFreq;
        }

        //complex enemy spawning
        complexEnemySpawnTimer += Gdx.graphics.getDeltaTime();
        if(complexEnemySpawnTimer >= 1 / complexEnemySpawnFreq) {
            complexEnemies.add(new ComplexEnemy());
            complexEnemySpawnTimer -= 1 / complexEnemySpawnFreq;
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
                    //destroy if out of health
                    if(enemy.health <= 0.0f) {
                        enemyIter.remove();
                        enemyExplosion.play();
                    }
                    break;
                }
            }
        }

        Iterator<Missile> missIter = player.missiles.iterator();

        while(missIter.hasNext()) {
            Missile missile = missIter.next();
            Iterator<Enemy> enemyIter = enemies.iterator();

            while(enemyIter.hasNext()) {
                Enemy enemy = enemyIter.next();
                //deal damage
                if(CheckCollision(missile.sprite, enemy.sprite)) {
                    enemy.TakeDamage(missile.damage);
                    //removing missile
                    missIter.remove();
                    //destroy if out of health
                    if(enemy.health <= 0.0f) {
                        enemyIter.remove();
                        enemyExplosion.play();
                    }
                    break;
                }
            }
        }
    }

    public void BananaUpdate() {
        for(FreeFallObject banana : bananas) {
            banana.Move();
            banana.Rotate();
        }
    }

    public void BananaCheckBounds() {
        Iterator<FreeFallObject> iter = bananas.iterator();

        while(iter.hasNext()) {
            FreeFallObject banana = iter.next();

            if(banana.posx < 0 || banana.posx > 1280 || banana.posy < -50 || banana.posy > 720)
                iter.remove();
        }
    }
}
