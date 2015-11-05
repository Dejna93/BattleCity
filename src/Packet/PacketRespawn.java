package Packet;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class PacketRespawn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5053922420301975115L;
	private Vector2 position;
	public PacketRespawn(Vector2 position) {
		// TODO Auto-generated constructor stub
		this.position = position;
	}
	public Vector2 getPosition()
	{
		return position;
	}

}
