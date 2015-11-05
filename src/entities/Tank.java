package entities;

import java.util.ArrayList;
import java.util.Random;

import handlers.BotEnemy;
import handlers.Collision;
import AssetsLoader.Assets;
import Tiles.Tile;
import Utils.*;
import Utils.Direction.direction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tank extends Entity {

	private Vector2 playerPos;
	private Vector2 futurePos;
	private BotEnemy bot;
	private boolean moveBlock = false;
	private direction d;
	private Tile[][] tMap = new Tile[20][15];
	private ArrayList<TankUtil> tanks = new ArrayList<TankUtil>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private Rectangle rPlayer;

	private Collision collision;
	private boolean shooted = false;
	private boolean isCollide = false;

	private Timer timer;

	public Tank(Rectangle rPlayer, Vector2 startPos) {
		Texture text = Assets.getManager().get(
				"assets/texture/enemy_tank_1.png", Texture.class);
		sprite = new Sprite(text);
		setPosition(startPos.x, startPos.y);

		x = startPos.x;
		y = startPos.y;
		playerPos = new Vector2(0, 0);
		futurePos = new Vector2(x, y);

		timer = new Timer(4);

		setDirection(direction.Up);
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		this.rPlayer = rPlayer;
	}

	public void setDirection(Direction.direction direction) {
		this.d = direction;
	}

	public direction getDirection() {
		return this.d;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void updateMap(Tile[][] tMap) {
		this.tMap = tMap;
	}

	public ArrayList<Bullet> getTankBullets() {
		return bullets;
	}

	public void moveTo(float vx, float vy) {

		if (x == vx) {
			if (y == vy)
				moveBlock = false;
		}

		if (x != vx || y != vy) {

			if (isCollide == false) {
				if (isLeft(vx)) {
					x -= 1;
					setDirection(Direction.direction.Left);
					sprite.setRotation(90);
					isCollide = false;
				} else if (isRight(vx)) {
					x += 1;
					setDirection(Direction.direction.Right);
					sprite.setRotation(-90);
					isCollide = false;
				} else if (isTop(vy)) {
					y += 1;
					setDirection(Direction.direction.Up);
					sprite.setRotation(0);
					isCollide = false;
				} else if (isBot(vy)) {
					y -= 1;
					setDirection(Direction.direction.Down);
					sprite.setRotation(180);
					isCollide = false;
				}

			}
		}
	}

	private void backMove(float vx, float vy) {

		if (isLeft(vx))
			x += 2;
		if (isRight(vx))
			x -= 2;
		if (isTop(vy))
			y -= 2;
		if (isBot(vy))
			y += 2;

	}

	private boolean isLeft(float vx) {
		if (x > vx)
			return true;
		return false;
	}

	private boolean isRight(float vx) {
		if (x < vx)
			return true;
		return false;
	}

	private boolean isTop(float vy) {
		if (y < vy)
			return true;
		return false;
	}

	private boolean isBot(float vy) {
		if (y > vy)
			return true;
		return false;
	}

	public void update(Vector2 pos, Rectangle rPlayer, ArrayList<TankUtil> tanks) {
		playerPos = pos;
		this.tanks = tanks;

		if (shooted == true && timer.finish() == true) {
			bullets.add(new Bullet(new Vector2(x, y), getDirection(), shooted));
		}

		for (int i = 0; i < bullets.size(); i++) {

			bullets.get(i).update(new Vector2(x, y));

		}

		if (x < 0) {
			x = 0;
			moveBlock = false;
		}
		if (x > WIDTH - getWidth()) {
			x = WIDTH - getWidth();
			moveBlock = false;
		}
		if (y < 0) {
			y = 0;
			moveBlock = false;
		}
		if (y > HEIGHT - getHeight()) {
			y = HEIGHT - getHeight();
			moveBlock = false;
		}

		this.rPlayer = rPlayer;
		sprite.setPosition(x, y);
		bounds.setX(x);
		bounds.setY(y);
		makeDecision();
		clean();
	}

	private void clean() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getPositon().x < 0) {
				// System.out.println("usuwa " + i);
				bullets.remove(i);
				break;
			} else if (bullets.get(i).getPositon().x > 640) {
				// System.out.println("usuwa " + i);
				bullets.remove(i);
				break;
			}
			/*
			 * else if(bullets.get(i).getPositon().y <0) {
			 * System.out.println("usuwa " + i); bullets.remove(i); break; }
			 * else if(bullets.get(i).getPosition().y > 480) {
			 * System.out.println("usuwa " + i); bullets.remove(i); break; }
			 */

		}
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public boolean isMoveBlock() {
		return moveBlock;
	}

	public void setMoveBlock(boolean moveBlock) {
		this.moveBlock = moveBlock;
	}

	private void setNext(Vector2 pos) {
		futurePos = pos;
	}

	public void makeDecision() {
		// System.out.println(futurePos.x + " " +futurePos.y);
		boolean makingDecision = true;
		collision = new Collision(tMap, tanks, playerPos);
		if (!moveBlock) {
			while (makingDecision) {
				float decision = randomState() * 100;// / 100;

				if (decision <= 60f) {

					if ((decision >= 0 && decision < 15f)) {
						// System.out.println("lewo");
						if (collision.possibleMove(x - 32, y)) {
							makingDecision = false;
							setNext(new Vector2(x - 32, y));
							moveBlock = true;
						}
					} else if (decision >= 15.0f && decision < 30.0f) {
						// System.out.println("prawo");
						if (collision.possibleMove(x + 32, y)) {
							makingDecision = false;
							setNext(new Vector2(x + 32, y));
							moveBlock = true;
						}
					} else if (decision >= 30.0f && decision < 45.0f) {
						// System.out.println("top");
						if (collision.possibleMove(x, y + 32)) {
							makingDecision = false;
							setNext(new Vector2(x, y + 32));
							moveBlock = true;
						}

					} else if (decision >= 45.0f && decision < 60.0f) {
						// System.out.println("dol");
						if (collision.possibleMove(x, y - 32)) {
							makingDecision = false;
							setNext(new Vector2(x, y - 32));
							moveBlock = true;
						}

					}

					// System.out.println(futurePos.x + " " + futurePos.y);
				} else if (decision > 60f) {
					if (decision > 98.0) {
						// System.out.println("Strzelam tank" + getPosition().x
						// + " " + getPosition().y);
						shooted = true;

					}
				}
			}
		}
		if (moveBlock) {
			moveTo(futurePos.x, futurePos.y);
		}
	}

	public void drawBullets(SpriteBatch sb) {
		for (int i = 0; i < bullets.size(); i++) {
			// System.err.println("draw bullets" + i);
			bullets.get(i).getSprite().draw(sb);
		}
	}

	public float randomState() {
		Random rand = new Random();
		return rand.nextFloat();
	}
}
