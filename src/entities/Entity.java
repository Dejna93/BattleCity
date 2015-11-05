package entities;

import ServerGame.PlayerData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected Sprite sprite;
	// protected SpriteBatch sb;

	// protected enum Direction {Left,Right,Up,Down};
	// protected Direction direction;
	protected Rectangle bounds;

	public float x, y;

	protected float speed;

	protected int WIDTH = Gdx.graphics.getWidth() - 160;
	protected int HEIGHT = Gdx.graphics.getHeight();

	protected void setPosition(float x, float y) {
		this.x += x;
		this.y += y;
	}

	protected void setPosition(Vector2 pos) {
		this.x = pos.x;
		this.y = pos.y;
		sprite.setPosition(x, y);

	}

	protected void setPosition(PlayerData data) {
		this.x += data.getPosition().x;
		this.y += data.getPosition().y;

	}

	protected Vector2 getPosition() {
		return new Vector2(x, y);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	protected float getX() {
		return x;
	}

	protected float getY() {
		return y;
	}

	protected float getWidth() {
		return sprite.getWidth();
	}

	protected float getHeight() {
		return sprite.getHeight();
	}

}
