package entities;

import handlers.Collision;

import java.util.ArrayList;

import AssetsLoader.Assets;
import Tiles.Tile;
import Utils.Direction.direction;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

	// skierowanie czolgu
	private direction playerDirect;

	private boolean shoot = false;
	private Rectangle enemy;
	// private ArrayList<Tile> tileMap;
	private Tile[][] tMap = new Tile[20][15];

	private ArrayList<Rectangle> aTanks = new ArrayList<Rectangle>();

	public float vx = 0, vy = 0;

	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	private Collision collision;

	public Player(Tile[][] tMap) {
		setPosition(200,32);
		this.tMap = tMap;
		speed = 2;

		Texture text = Assets.getManager().get("assets/texture/player_up.png",
				Texture.class);

		sprite = new Sprite(text);
		// this.aBlocks = aBlocks;

		setPlayerDirect(playerDirect.Up);

		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		enemy = new Rectangle();

	}

	public void setShoot(boolean state) {
		shoot = state;
	}

	public void removeTank() {
		aTanks.clear();
	}

	private boolean isCollision(Rectangle enemy) {
		if (bounds.overlaps(enemy))
			return true;
		return false;
	}

	public int bulletsize() {
		return bullets.size();
	}

	public Sprite getSB() {
		return sprite;
	}

	public void getTanks(ArrayList<Tank> aEnemies) {
		for (int i = 0; i < aEnemies.size(); i++)
			this.aTanks.add(aEnemies.get(i).getBounds());
	}

	private void setRotation(direction direction) {
		if (direction == direction.Up) {
			sprite.setRotation(0);
		}
		if (direction == direction.Down) {
			sprite.setRotation(180);
		}
		if (direction == direction.Left) {
			sprite.setRotation(90);
		}
		if (direction == direction.Right) {
			sprite.setRotation(-90);
		}
		setPlayerDirect(direction);
	}

	public int getBulletHight() {
		return 4;
	}

	public Vector2 getBulletPosition(int id) {
		try {
			return bullets.get(id).getPositon();
		} catch (Exception e) {
			return new Vector2(-1, -1);
		}
	}

	private Vector2 getMove(direction direction) {
		// TODO Auto-generated method stub

		if (direction == direction.Up) {
			return new Vector2(0, speed);
		}
		if (direction == direction.Down) {
			return new Vector2(0, -speed);
		}
		if (direction == direction.Left) {
			return new Vector2(-speed, 0);
		}
		if (direction == direction.Right) {
			return new Vector2(speed, 0);
		}

		return new Vector2(0, 0);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setMove(direction direction) {

		Rectangle futurePosition = new Rectangle(getX() + getMove(direction).x,
				getY() + getMove(direction).y, getWidth(), getHeight());
		boolean isBlock = false;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 15; j++) {
				if (tMap[i][j].getBounds().overlaps(futurePosition)
						&& (tMap[i][j].getOptions().equals("shotable") || tMap[i][j]
								.getOptions().equals("block"))) {
					setRotation(direction);
					isBlock = true;
					break;
				}
			}
		}
		for (int i = 0; i < aTanks.size(); i++) {
			if (aTanks.get(i).overlaps(futurePosition)) {
				isBlock = true;
			}

		}
		if (isBlock == false) {
			setRotation(direction);
			move(getMove(direction));
		}

	}

	private void move(Vector2 move) {
		// TODO Auto-generated method stub
		this.x += move.x;
		this.y += move.y;
		validposition();
	}

	protected void move(float vx, float vy) {
		this.x += vx;
		this.y += vy;

		// validposition();

	}

	public void update(Tile[][] tMap, ArrayList<Rectangle> tanks) {
		
		this.tMap = tMap;
		this.aTanks = tanks;
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(getPosition());
			shoot = bullets.get(i).getShoot();
		}

		bounds.x = x;
		bounds.y = y;

		sprite.setPosition(x, y);

	}

	private void validposition() {

		if (x < 0) {
			x = 0;
			vx = 0;
		}
		if (x > WIDTH - getWidth()) {
			x = WIDTH - getWidth();
			vx = 0;
		}
		if (y < 0) {
			y = 0;
			vy = 0;
		}
		if (y > HEIGHT - getHeight()) {
			y = HEIGHT - getHeight();
			vy = 0;
		}
	}

	public void shoot() {
		// ..bullet.shoot(getPosition(),playerDirect);
		System.out.println(shoot);
		bullets.add(new Bullet(getPosition(), getPlayerDirect(), true));
		shoot = true;
	}

	public void drawBullets(SpriteBatch sb) {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).getSprite().draw(sb);
			;
		}
	}

	public Rectangle getBoundsBullet(int id) {
		if (bullets.get(id) != null) {
			return bullets.get(id).getBounds();
		}
		return new Rectangle();
	}

	public void removeBullet(int id) {
		if (bullets.get(id) != null) {
			bullets.clear();
			setShoot(false);

		}
	}

	public direction getPlayerDirect() {
		return playerDirect;
	}

	public void setPlayerDirect(direction playerDirect) {
		this.playerDirect = playerDirect;
	}

	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return new Vector2(x, y);
	}

	public boolean isShooting() {
		// TODO Auto-generated method stub

		return shoot;
	}

}
