package Packet;

import java.io.Serializable;

public class PacketWiner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1006595608692653161L;
	
	private String winerName= "";
	public PacketWiner(String winerName) {
		// TODO Auto-generated constructor stub
		this.winerName = winerName;
	}
	
	public String getWinerName()
	{
		return winerName;
	}

}
