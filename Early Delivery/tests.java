public class ViralTest {
	@Test public void Collision() {
		Player player = new Player();
		player.setPosition(0.0f, 0.0f);
		Enemy enemy = new Enemy();
		enemy.setPosition(0.0f, 0.0f);
		Projectile projectile = new Projectile(0.0f, 0.0f, 0.0f);
		Missile missile = new Missile(0.0f, 0.0f, 0.0f);
		
		AssertTrue(CheckCollision(player.getSprite(), enemy.getSprite()));
		AssertTrue(CheckCollision(enemy.getSprite(), Projectile.getSprite()));
		AssertTrue(CheckCollision(projectile.getSprite(), missile.getSprite()));
		AssertTrue(CheckCollision(missile.getSprite(), player.getSprite()));
	}
	
	@Test public void Shooting() {
		Player player = new Player();
		player.Shoot();
		player.Shoot();
		player.Shoot();
		player.ShootMissiles();
		player.ShootMissiles();
		player.ShootMissiles();
		
		AssertEquals(player.projectiles.size, 3);
		AssertEquals(player.missiles.size, 3);
	}
	
	@Test public void Destruction() {
		WorldController wc = new WorldController();
		wc.player.setPosition(0.0f, 0.0f);
		wc.player.Shoot();
		wc.player.Shoot();
		wc.player.Shoot();
		wc.player.Shoot();
		wc.player.Shoot();
		wc.enemies.add(new Enemy());
		wc.enemies.getElementAt(0).setPosition(0.0f, 0.0f);
		wc.EnemyDamage();
		
		AssertEquals(wc.enemies.size(), 0);
	}
}