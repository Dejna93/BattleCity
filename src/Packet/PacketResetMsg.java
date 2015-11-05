package Packet;

import java.io.Serializable;

import Utils.Message;

public class PacketResetMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4897674165685978066L;
	

	private Message message;
	private boolean show= true;
	private boolean reset;
	public PacketResetMsg(String msg,boolean state,boolean reset) {
		// TODO Auto-generated constructor stub
		message = new Message(msg,state);
		this.reset = reset;
	}
	public boolean getReset()
	{
		return reset;
	}

	public Message getMessage()
	{
		return message;
	}
	public boolean getShow()
	{
		return show;
	}

}
