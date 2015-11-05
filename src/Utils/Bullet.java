package Utils;

import java.io.Serializable;

import Utils.Direction.direction;

import com.badlogic.gdx.math.Vector2;

public class Bullet implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3870150660038839395L;
	
	private Vector2 pos;
	private direction dire;
	public Bullet(Vector2 pos,direction direction) {
		// TODO Auto-generated constructor stub
		this.pos = new Vector2(pos);
		this.dire = direction;	
	}
	public Vector2 getPosition()
	{
		return pos;
	}
	public direction getDirection()
	{
		return dire;
	}
}
