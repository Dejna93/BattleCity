package Packet;

import java.io.Serializable;

public class PacketChat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2310677366178403661L;

	private String userName;
	private String time;
	private String message;
	
	public PacketChat(String userName,String time, String message) {
		// TODO Auto-generated constructor stub
		this.userName = userName;
		this.time = time;
		this.message = message;
	}
	
	public void setMessage(String userName, String time , String message)
	{
		this.userName = userName;
		this.time = time;
		this.message = message;
	}
	public String getMessage()
	{
		return message;
	}
	public String getUsername()
	{
		return userName;
	}
	public String getTime()
	{
		return time;
	}
	
}
