package Utils;

import Utils.Direction.direction;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import AssetsLoader.Assets;

import entities.Entity;


public class BulletsSprite extends Entity{

	
	public BulletsSprite()
	{
		
	}
	public BulletsSprite(Vector2 position,direction direction) {
		// TODO Auto-generated constructor stub
		Texture text = Assets.getManager().get("assets/texture/bullet.png", Texture.class);
		
		sprite = new Sprite(text);
		
		setRotation(direction);
		this.x = position.x;
		this.y = position.y;
		sprite.setPosition(x, y);
	//	System.err.println("bulletsspirte " +x + " " + y + " " + position.x + " " + position.y );
		//setRotation(directionB);
		
	}

	public Sprite getSprite()
	{
		return sprite;
	}
	private void setRotation(direction direction)
	{
		if(direction == direction.Up)
		{
		
			sprite.setRotation(0);
		}
		if(direction == direction.Down)
		{
			
			sprite.setRotation(180);
		}
		if(direction == direction.Left)
		{
		
			sprite.setRotation(90);
		}
		if(direction == direction.Right)
		{
		
			sprite.setRotation(-90);
		}
	}
}
