package Packet;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class PacketAnimation implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1259211851794584150L;
	
	private Vector2 position = new Vector2();
	private int id;
	public PacketAnimation(Vector2 position,int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.position = position;
	}
	public  Vector2 getPosition() {
		return position;
	}
	public int getType()
	{
		return id;
	}

}
