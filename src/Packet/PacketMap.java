package Packet;

import java.io.Serializable;



import Tiles.Tile;
import Utils.MapFromServer;

public class PacketMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1813471245174344671L;

	private MapFromServer mapGame;
	
	
	public PacketMap(MapFromServer map) {
		// TODO Auto-generated constructor stub
		this.mapGame = map;
	}
	public void setNewMap(MapFromServer map)
	{
		this.mapGame = map;
	}
	public MapFromServer getMap()
	{
		return mapGame;
	}
	public Tile[][] getTiles()
	{
		return mapGame.getMap();
	}
}
