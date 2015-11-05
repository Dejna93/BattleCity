package Packet;

import java.io.Serializable;

public class PacketRegister implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4701863697318219212L;

	String username;
	String password;
	String date;
	
	String [] data;
	public PacketRegister(String username,String password,String date) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.date = date;
		
		//----------------
		data = new String[3];
		data[0] = username;
		data[1] = password;
		data[2] = date;
	}
	public String [] getData()
	{
		return data;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public String getDate()
	{
		return date;
	}
	
}
