package entities;

import AssetsLoader.Assets;
import ServerGame.PlayerData;
import Utils.Direction.direction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyMulti extends Entity {

	int ID = -1;
	private String username;

	public EnemyMulti(PlayerData data) {
		// TODO Auto-generated constructor stub
		this.ID = data.getID();
		this.x = data.getPosition().x;
		this.y = data.getPosition().y;

		username = data.getUsername();

		sprite = new Sprite(Assets.getManager().get(
				"assets/texture/enemy_tank_1.png", Texture.class));
		setDirection(data.getDirection());
		sprite.setPosition(x, y);
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());

	}

	public Vector2 getPosition() {
		return new Vector2(x, y);
	}

	public void update(Vector2 pos) {
		move(pos);
		sprite.setPosition(x, y);
	}

	public String getNick()
	{
		return username;
	}
	public EnemyMulti getEnemy(PlayerData data) {
		return new EnemyMulti(data);
	}

	public int getID() {
		return ID;
	}

	private void move(Vector2 pos) {
		// TODO Auto-generated method stub
		this.x = pos.x;
		this.y = pos.y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	private void setDirection(direction direction) {
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
	}

}
