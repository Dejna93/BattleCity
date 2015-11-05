package Packet;

import java.io.Serializable;


import com.badlogic.gdx.math.Rectangle;

import Tiles.Tile;

public class PacketTileUpdate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2742564127651262004L;
	
	private Rectangle bounds;
	private int type;
	private int i;
	private int j;
	private String option;
	public PacketTileUpdate(Tile tile,int i,int j) {
		// TODO Auto-generated constructor stub
		this.bounds = tile.getBounds();
		this.type = tile.getType();
		this.option = tile.getOptions();
		this.i= i;
		this.j= j;
	}

	public Rectangle getBounds()
	{
		return bounds;	 
	}
	public int getType()
	{
		return type;
	}
	public String getOption()
	{
		return option;
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
