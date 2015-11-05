package Packet;

import java.io.Serializable;

import Utils.Direction.direction;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.BulletMulti;

public class PacketShoot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2121398715506462635L;
	private Vector2 position;
	private direction direction;
	private Rectangle bounds;
	private int ID;
	public PacketShoot(BulletMulti bullet) {
		// TODO Auto-generated constructor stub
		this.position= bullet.getPosition();
		this.direction = bullet.getDirection();
		this.bounds = bullet.getBounds();
		this.ID = bullet.getID();
	}
	
	public BulletMulti getBullet()
	{
		return new BulletMulti(ID, position, direction);
	}
	public Vector2 getPosition()
	{
		return position;
	}
	
}
