package handlers;



import java.net.ConnectException;
import java.util.ArrayList;

import network.Connection;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import Packet.PacketTile;
import ServerGame.MapGame;
import Tiles.Tile;
import Utils.MapFromServer;

public class MapHandler {

	private Tile[][] tMap = new Tile[20][15];
	
	private boolean updateTile = false;
	private ArrayList<Vector2> updateTiles= new ArrayList<Vector2>();
	 public MapHandler( MapFromServer map) {
		// TODO Auto-generated constructor stub
		
		
		 for(int i=0;i< 20; i++)
		 {
			 for (int j=0;j < 15; j++)
			 {
				 tMap[i][j] = new Tile(map.getTile(i, j));
			 }
		 }
	}
	 
	 public Tile[][] getMap()
	 {
		 return tMap;
	 }
	 public Tile getTile(int i , int j )
	 {
		 return tMap[i][j];
	 }
	 public void update(Connection conn)
	 {
		PacketTile pack = conn.getUpdateTile();
		if(pack.size() > 0)
		{
			tMap[pack.getI()][pack.getJ()] = pack.getTile();
			updateTiles.add(new Vector2(pack.getTile().getBounds().x,pack.getTile().getBounds().y));
			updateTile = true;
		}
		if(conn.isReset)
		{	//tMap = conn.getMap().getMap();	
		System.out.println("hanldermap update");
			//MapGame map = conn.getMap();
			//this.tMap = conn.getMap();
			 for(int i=0;i< 20; i++)
			 {
				 for (int j=0;j < 15; j++)
				 {
					 tMap[i][j] = new Tile(conn.getMap().getTile(i, j));
				 }
			 }
		conn.isReset= false;
		}

	 }
	 public boolean getUpdate()
	 {
		 return updateTile;
	 }
	
	 public ArrayList<Vector2> getWaterTiles()
	 {
		 ArrayList<Vector2> temp = new ArrayList<Vector2>();
		 for(int i=0;i< 20; i++)
		 {
			 for (int j=0;j < 15; j++)
			 {
				 if(tMap[i][j].getType()==5)
				 {
					 temp.add(new Vector2(tMap[i][j].getBounds().x,tMap[i][j].getBounds().y)); 
				 }
			}
		 }
		 return temp;
		 
	 }
	 public void draw(SpriteBatch sb)
	 {
		for (int i=0 ; i < 20 ; i++)
		 {
			for(int j=0; j < 15; j++)
			 tMap[i][j].draw(sb);
		 }
	 }
}
