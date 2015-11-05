package Packet;

import java.io.Serializable;

import Utils.Message;

public class PacketMessage implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2986388575066836563L;

	private Message message;
	private boolean show= true;
	public PacketMessage(String msg,boolean state) {
		// TODO Auto-generated constructor stub
		message = new Message(msg,state);
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
