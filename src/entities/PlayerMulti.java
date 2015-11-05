package entities;




import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import AssetsLoader.Assets;
import ServerGame.PlayerData;

import Utils.Direction.direction;

public class PlayerMulti extends Entity {

	private direction playerDirect;


	public float vx = 0, vy = 0;
	String username;
	
	
	public PlayerMulti() {
		// TODO Auto-generated constructor stub

	
		speed = 4;

		sprite = new Sprite(Assets.getManager().get("assets/texture/player_up.png", Texture.class));
		// this.aBlocks = aBlocks;
		sprite.setOrigin(16, 16);
	//	sprite.setCenter(16, 16);
		setPlayerDirect(playerDirect.Up);

		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		
	}
	
	
	
	


	public Sprite getSB() {
		return sprite;
	}

	public void move(Vector2 move) {
		this.x = move.x+16;
		this.y = move.y+16;
	

	}

	public void update(PlayerData data) {
	
	//	System.out.println("Update " + moved.x + " " + moved.y + " " +x +" " + y );
		username = data.getUsername();
		setPosition(data.getPosition());
		
		setDirection(data.getDirection());
		bounds.setPosition(x+16, y+16);

	}

	public String getNick()
	{
		return username;
	}
	public direction getPlayerDirect() {
		return playerDirect;
	}

	public void setPlayerDirect(direction playerDirect) {
		this.playerDirect = playerDirect;
	}

	private void setDirection(direction direction)
	{
		if(direction == direction.Up)
		{
			playerDirect = direction;
			sprite.setRotation(0);
		}
		if(direction == direction.Down)
		{
			playerDirect = direction;
			sprite.setRotation(180);
		}
		if(direction == direction.Left)
		{
			playerDirect = direction;
			sprite.setRotation(90);
		}
		if(direction == direction.Right)
		{
			playerDirect = direction;
			sprite.setRotation(-90);
		}
	}
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return new Vector2(x, y);
	}

	public Rectangle getBounds()
	{
		return bounds;
	}
}
