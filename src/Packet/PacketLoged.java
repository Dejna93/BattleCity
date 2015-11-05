package Packet;

import java.io.Serializable;
import java.util.ArrayList;



import com.badlogic.gdx.math.Vector2;

import ServerGame.MapGame;
import ServerGame.PlayerData;
import Utils.MapFromServer;

public class PacketLoged extends Packets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2125664859482896161L;
	
	private boolean isLogin = false;
	private Vector2 position;
	private ArrayList<PlayerData> playersConnected;
	private MapFromServer mapGame;
	public PacketLoged(int ID,boolean isLogin,ArrayList<PlayerData> players, MapFromServer map) { //good login
		// TODO Auto-generated constructor stub
		this.isLogin = isLogin;
		//playersConnected.get(ID).getPosition();
		playersConnected = new ArrayList<PlayerData>(players);
		this.mapGame = map;
		//this.playersConnected = playersConnected;
	
	}
	public PacketLoged(boolean isLogin) { //bad login
		// TODO Auto-generated constructor stub
		this.isLogin = isLogin;
	
	}
	
	public boolean getLogin()
	{
		return isLogin;
	}
	public Vector2 getPosition(int ID)
	{
		return playersConnected.get(ID).getPosition();
	}
	public ArrayList<PlayerData> getPlayers()
	{
		return playersConnected;
	}
	public MapFromServer getMap()
	{
		return mapGame;
	}

}
