package Packet;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class PacketMove implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 193649143020388578L;

	private int ID;
	private Vector2 position;
	public PacketMove(int ID, Vector2 position) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.position = position;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
	public int getID()
	{
		return ID;
	}
	
}
