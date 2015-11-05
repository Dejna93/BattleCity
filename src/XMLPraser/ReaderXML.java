package XMLPraser;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.*;

import Tiles.Tile;
import Utils.TypeTile;

public class ReaderXML implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8814728525051058531L;

	private TypeTile types = new TypeTile();
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private HashMap<Integer, String> option = new HashMap<Integer, String>();
	private ArrayList<TypeTile.typeTile> typeTile = new ArrayList<TypeTile.typeTile>();
	private ArrayList<Vector2> spawnPoints = new ArrayList<Vector2>();
	private ArrayList<Vector2> enemySettings = new ArrayList<Vector2>();
	// public ArrayList<Element> tile = new ArrayList<XmlReader.Element>();
	private String filepath;

	private Tile[][] tMap = new Tile[20][15];

	public Vector2 getSpawn(int id) {
		return spawnPoints.get(id);
	}

	public ArrayList<TypeTile.typeTile> getTypeTile() {
		return typeTile;
	}

	public ArrayList<Vector2> getSpawnPoints() {
		return spawnPoints;
	}

	public ArrayList<Vector2> getEnemySettings() {
		return enemySettings;
	}

	public ReaderXML() {
		// TODO Auto-generated constructor stub
		filepath = "";
	}

	public ReaderXML(String filepath) {
		this.filepath = filepath;
	}

	public void setFile(String file)
	{
		this.filepath = file;
	}
	public Tile[][] loader(boolean isSingle) throws IOException {
		try {
			XmlReader reader = new XmlReader();

			Element root = reader.parse(new FileHandle(filepath));

			Element data = root.getChildByName("data");
			Element options = root.getChildByName("option");

			Element typeT = root.getChildByName("typetile");
			Element spawnpoints = root.getChildByName("spawnpoint");
			Element enemyS = root.getChildByName("enemysettings");

			// for (int i=0 ; i < typeT.getChildCount() ; i++)
			// {
			// typeTile.add(types.getType(typeT.getChild(i).getAttribute("enum")));
			// System.out.println("type " + typeTile.get(i).name());
			// }
			for (int i = 0; i < spawnpoints.getChildCount(); i++) {
				spawnPoints.add(new Vector2(spawnpoints.getChild(i)
						.getIntAttribute("x"), spawnpoints.getChild(i)
						.getIntAttribute("y")));
				System.out.println("SpawnPoints " + spawnPoints.get(i).x + " "
						+ spawnPoints.get(i).y);
			}
			for (int i = 0; i < enemyS.getChildCount(); i++) {
				enemySettings.add(new Vector2(enemyS.getChild(i)
						.getIntAttribute("id"), enemyS.getChild(i)
						.getIntAttribute("count")));
			}
			for (int i = 0; i < options.getChildCount(); i++) {
				option.put(options.getChild(i).getIntAttribute("type"), options
						.getChild(i).getAttribute("option"));
				// System.out.println(i + " "
				// +options.getChild(i).getAttribute("type") + " "+
				// options.getChild(i).getAttribute("option"));
			}
			int x = 0;
			int y = 0;
			for (int i = 0; i < data.getChildCount(); i++) {
				
				if (x < 20) {

					tMap[x][y] = new Tile(
							data.getChild(i).getIntAttribute("x"), data
									.getChild(i).getIntAttribute("y"), data
									.getChild(i).getIntAttribute("type"),
							option, isSingle);
					System.out.println(x+ " " + y + tMap[x][y].getType());
					x++;
				}
				if (x == 20) {
					x = 0;
					y++;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
			return tMap;
		
	}


	public String getType(int id) {
		return option.get(id);
	}
	/*
	 * public static void main(String[] args) throws IOException { new
	 * ReaderXML();
	 * 
	 * System.exit(0); }
	 */

}
