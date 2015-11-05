package entities;

import java.io.Serializable;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import Utils.Direction.direction;

public class BulletMulti extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1719475888281586139L;

	private int idPlayer = -1;
	private Vector2 playerPosition = new Vector2();

	private direction shootDirection;

	public BulletMulti(int idPlayer, Vector2 playerPosition,
			direction playerDirection) {
		// TODO Auto-generated constructor stub
		System.err.println(playerDirection);
		this.idPlayer = idPlayer;
		this.playerPosition = playerPosition;
		this.x = playerPosition.x + 8;
		this.y = playerPosition.y + 8;
		bounds = new Rectangle(x, y, 0.5f, 0.5f); // change next time
		int speed = 5;
		this.shootDirection = playerDirection;

	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		return new Vector2(x, y);
	}

	public direction getDirection() {
		return shootDirection;
	}

	public int getID() {
		return idPlayer;
	}

	private void move(Vector2 pos) {

		this.x += pos.x;
		this.y += pos.y;
		bounds.setPosition(x, y);
		// System.out.println(bounds.x + " " + bounds.y + " " + bounds.width +
		// " " + bounds.height);
	}

	public void update() {

		if (shootDirection == direction.Up) {
			move(new Vector2(0, 5));

		}

		if (shootDirection == direction.Down) {
			move(new Vector2(0, -5));
		}
		if (shootDirection == direction.Left) {
			move(new Vector2(-5, 0));
		}
		if (shootDirection == direction.Right) {
			move(new Vector2(5, 0));
		}

	}

	protected void setPosition(Vector2 position) {
		this.x = position.x;// center bullet
		this.y = position.y;

	}
}
