package Packet;

import java.io.Serializable;

public class PacketRetryGame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2347454585073737124L;
	private boolean state;
	private int port;
	public PacketRetryGame(boolean retry, int port) {
		// TODO Auto-generated constructor stub
		this.state = retry;
		this.port = port;
	}
	public boolean getReset()
	{
		return state;
	}
	public int getPort()
	{
		return port;
	}

}
