package ServerGame;

import handlers.LevelControler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import Packet.PacketTile;
import Tiles.Tile;
import XMLPraser.ReaderXML;

public class MapGame implements Serializable {
	/**
	 * 
	 */
	private LevelControler levelControler;
	private static final long serialVersionUID = 4001699460120474088L;
	// 20x15

	private Tile[][] tMap= new Tile[20][15];
	private ReaderXML levelLoaded = new ReaderXML("levels/level2.xml");

	private boolean isLoad = false;
	private ArrayList<Vector2> updateTile = new ArrayList<Vector2>();

	public MapGame(int i)
	{
		
	}
	
	
	public MapGame() {
		// TODO Auto-generated constructor stub
		levelControler = new LevelControler();
		try {
			if(isLoad== false)
			{
			levelLoaded.setFile(levelControler.getLevel());
			tMap = levelLoaded.loader(false);
			isLoad=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// = levelLoaded.loader(false);
	}
	public int getCount()
	{
		return levelControler.getCount();
	}
	public void resetMap()
	{
		try{
			levelControler.update();
			levelLoaded.setFile(levelControler.getLevel());
			tMap = levelLoaded.loader(false);
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String getCurrentNameLevel()
	{
		return levelControler.getNameCurrentLevel();
	}
	public Vector2 getSpawnPosition(int id) {
		return levelLoaded.getSpawn(id);
	}

	public ArrayList<Vector2> getSpawn()
	{
		return levelLoaded.getSpawnPoints();
	}
	public Tile[][] getMap() {
		return tMap;
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



	public Tile getTile(int i,int j) {
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
