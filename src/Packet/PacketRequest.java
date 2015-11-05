package Packet;

import java.io.Serializable;

public class PacketRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4846005797480531644L;

	private boolean request;
	public PacketRequest(boolean state) {
		// TODO Auto-generated constructor stub
		this.request = state;
	}
	public boolean getRequest()
	{
		return request;
	}
}

