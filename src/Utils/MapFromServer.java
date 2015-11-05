package Utils;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import Packet.PacketTile;
import Tiles.Tile;

public class MapFromServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4671209872442534534L;

	
	private Tile[][] tMap= new Tile[20][15];
	private ArrayList<Vector2> updateTile = new ArrayList<Vector2>();
	
	public MapFromServer() {
		// TODO Auto-generated constructor stub
	}
	public MapFromServer(Tile[][] tMap ) {
		// TODO Auto-generated constructor stub
		for(int i=0 ; i < 20; i++)
		{
			for(int j=0; j < 15; j++){
				this.tMap[i][j] = tMap[i][j];
			}
		}
	}
	
	public PacketTile getUpdateTile() {
		PacketTile packetTile = new PacketTile();

		for (int i = 0; i < updateTile.size(); i++) {
			int x = (int) updateTile.get(i).x;
			int y =  (int) updateTile.get(i).y;
			packetTile = new PacketTile(x,y, tMap[x][y]);
		//	packetTile = new PacketTile(updateTile.get(i),
		//			mapLoad.get(updateTile.get(i)));

		}
		updateTile.clear();
		return packetTile;
	}
	
	
	public Tile[][] getMap()
	{
		return tMap;
	}
	public Tile getTile(int i, int j)
	{
		return tMap[i][j];
	}
	
	
	public void setTile(Tile tile, int i,int j) {
		tMap[i][j] = tile;
		
	}

	public void setTile(Rectangle bounds, int type, String option, int i,int j) {
		// TODO Auto-generated method stub

		tMap[i][j] = new Tile(bounds, type, option);
		updateTile.add(new Vector2(i,j));

	}
}


