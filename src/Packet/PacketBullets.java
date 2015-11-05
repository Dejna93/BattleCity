package Packet;

import java.io.Serializable;
import java.util.ArrayList;


import com.badlogic.gdx.math.Vector2;


import ServerGame.ShootManager;
import Utils.Bullet;
import Utils.Direction.direction;

public class PacketBullets implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6835572391257177752L;
	
	private ArrayList<Bullet> bullets;
	
	
	
	
	public PacketBullets(ShootManager shootManager) {
		// TODO Auto-generated constructor stub

		bullets = new ArrayList<Bullet>();
		for (int i=0; i < shootManager.size() ; i++)
		{
			bullets.add(new Bullet(shootManager.getPosition(i), shootManager.getDirection(i)));
		}
	//	positions.add(new Vector2(shootManager.getBullets().get(0).getPosition()));
	}
	public int size()
	{
		return bullets.size();
	}
	public direction getDirection(int id)
	{
		return bullets.get(id).getDirection();
	}
	public ArrayList<Bullet> getBullets()
	{
		return bullets;
	}
	
	public Vector2 getPosition(int id)
	{
		return bullets.get(id).getPosition();
	}

	


}
