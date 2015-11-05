package Packet;

import java.io.Serializable;

public class PacketDisconnect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2924388789228934964L;
	private boolean toClose= false;
	public PacketDisconnect(boolean toClose) {
		// TODO Auto-generated constructor stub
		this.toClose = toClose;
	}
	public boolean getClose()
	{
		return toClose;
	}
	public void setClose(boolean isClose)
	{
		this.toClose = isClose;
	}
}
