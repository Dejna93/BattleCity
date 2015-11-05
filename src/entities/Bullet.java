package entities;

import Utils.Direction.direction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity {

	// private float x,y;

	private float speed;

	private direction shootDirection;

	private Vector2 playerPosition;
	private boolean hited, shooted = false;

	public Bullet() {

	}

	public Bullet(Vector2 position, direction playerDirection, boolean shooted) {

		sprite = new Sprite(new Texture("assets/texture/bullet.png"));
		setShootDirection(playerDirection);
		speed = 5;
		this.playerPosition = position;
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());

		x = position.x + 16;
		y = position.y + 16;
		// System.out.println(position.x + " " + position.y + " "+ x + " " + y);
		this.shooted = shooted;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void move(Vector2 v) {
		x += v.x;
		y += v.y;
	}

	// dodanie funkcji do kolizji bulleta z usuwaniem go
	public void update(Vector2 playerPosition) {
		this.playerPosition = playerPosition;

		if (shooted) {
			switch (shootDirection) {
			case Up:
				move(new Vector2(0, speed));
				setRotation(0);
				break;
			case Down:
				move(new Vector2(0, -speed));
				setRotation(180);
				break;
			case Left:
				move(new Vector2(-speed, 0));
				setRotation(90);
				break;
			case Right:
				move(new Vector2(speed, 0));
				setRotation(-90);
				break;

			default:
				break;
			}
		}
		// show();

		updateBounds();
		sprite.setPosition(x, y);
		clean();

	}

	private void updateBounds() {
		bounds.setX(x);
		bounds.setY(y);
	}

	private void setRotation(float angle) {
		sprite.setRotation(angle);
	}

	protected void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	private void clean() {
		if (x < -10) {
			hited = true;
			shooted = false;
		}
		if (x > WIDTH+10) {
			hited = true;
			shooted = false;
		}
		if (y < -10) {
			hited = true;
			shooted = false;

		}
		if (y > HEIGHT+10) {
			hited = true;
			shooted = false;
		}
	}

	public Vector2 getPositon() {
		return new Vector2(x, y);
	}

	public boolean getShoot() {
		return shooted;
	}

	protected float getWidth() {
		return sprite.getWidth();
	}

	protected float getHeight() {
		return sprite.getHeight();
	}

	public direction getShootDirection() {
		return shootDirection;
	}

	public void setShootDirection(direction shootDirection) {
		this.shootDirection = shootDirection;
	}

}
