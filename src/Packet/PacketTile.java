package Packet;

import java.io.Serializable;
import java.util.ArrayList;

import Tiles.Tile;

public class PacketTile implements Serializable {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 4206627668937282343L;
	/**
	 * 
	 */
	
	private Tile tMap= new Tile();
	
	private int i,j;

	private int size =0 ;
	public PacketTile() {
		// TODO Auto-generated constructor stub
	}
	
	public PacketTile(int i,int j,Tile tile) {
		// TODO Auto-generated constructor stub
		tMap = tile;
		this.i = i;
		this.j = j;
		size++;
	}

	public int size()
	{
		return size;
	}
	public Tile getTile()
	{
		return tMap;
	}
	public int getI()
	{
		return i;
	}
	public int getJ()
	{
		return j;
	}
}

