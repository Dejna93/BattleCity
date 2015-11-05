package Packet;

import java.io.Serializable;

public class PacketLogin extends Packets implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7744242982222857658L;

	private String username;
	private String password;
	private int ID;
	
	public PacketLogin(int ID) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		setType(Type.LOGIN);
	}
	public int getID()
	{
		return ID;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	
}
