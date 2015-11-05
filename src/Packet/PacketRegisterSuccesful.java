package Packet;

import java.io.Serializable;

public class PacketRegisterSuccesful implements Serializable{

	/**
	 * 
	 */
	private boolean isRegister;
	private static final long serialVersionUID = -7276519205664320159L;
	
	public PacketRegisterSuccesful(boolean state) {
		// TODO Auto-generated constructor stub
		this.isRegister = state;
	}
	public boolean getState()
	{
		return isRegister;
	}

}
