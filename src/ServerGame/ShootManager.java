package ServerGame;

import handlers.DatebaseHandler;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.Sys;

import Packet.PacketAnimation;
import Packet.PacketRespawn;
import Packet.PacketTileUpdate;
import Utils.ScoreType;
import Utils.Direction.direction;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.BulletMulti;

public class ShootManager {

	ArrayList<BulletMulti> bullets = new ArrayList<BulletMulti>();
	boolean isShoot = false;
	private ScoreBoard scoreBoard = new ScoreBoard();

	private ArrayList<Vector2> respawnPoints;
	public ShootManager(ScoreBoard score,DatebaseHandler db,ArrayList<Vector2> respawnPoints) {
		this.scoreBoard = score;
		this.respawnPoints = respawnPoints;
	}

	public ShootManager(BulletMulti bullet,DatebaseHandler db,ArrayList<Vector2> respawnPoints) {
		// TODO Auto-generated constructor stub
		this.respawnPoints = respawnPoints;

	}

	public int size() {
		return bullets.size();
	}

	public ScoreBoard getUpdateScore()
	{
		return scoreBoard;
	}
	private boolean isHear(int port) {
		for (int i = 0; i < bullets.size(); i++) {
			if (port == bullets.get(i).getID())
				return true;
		}
		return false;
	}

	public void update(MapGame map, Server server,Players players,ArrayList<Vector2> respawn) {

		ArrayList<Vector2> toSend = new ArrayList<Vector2>();
		this.respawnPoints = respawn;
		
		for (int i = 0; i < bullets.size(); i++) {
			System.out.println(bullets.get(i).getBounds().x + " " + bullets.get(i).getBounds().y + " " + bullets.get(i).getPosition().x + " " + bullets.get(i).getPosition().y  );
			
			if (bullets.get(i).getPosition().x > 800
					|| bullets.get(i).getPosition().x < -10
					|| bullets.get(i).getPosition().y > 600
					|| bullets.get(i).getPosition().y < -10) {
				bullets.remove(i);
				isShoot = false;
				break;
			}
			bullets.get(i).update();

		}
		
		
		if(players.size() >1){
		for (int i=0 ; i < bullets.size() ; i++)
		{
			for(int j=0 ; j < players.size() ; j++)
			{
				
				//System.out.println(bullets.get(i).getID() + " " + players.getPlayerID(j) );
				if(bullets.get(i).getBounds().overlaps(players.getPlayerBounds(j)) && bullets.get(i).getID() != players.getPlayerID(j))
				{	
					server.broadcast(new PacketAnimation(players.getPlayerPosition(j),1));
					
					players.getPlayer(j).respawn(possibleRespawn(map, players,players.getPlayerID(j)));
					server.broadcast(new PacketRespawn(players.getPlayerPosition(j)));
					players.setScore(bullets.get(i).getID(), 100);

					bullets.remove(i);
					break;
				}
			}
		}
		}
		
		for (int i = 0; i < 20; i++) {

			for (int j = 0; j < 15; j++) {
				for (int k = 0; k < bullets.size(); k++) {
					if (bullets.get(k).getBounds().overlaps(map.getTile(i, j).getBounds()) && map.getTile(i, j).getOptions().equals("shotable"))
					//if(isCollide(bullets.get(k).getPosition(), map.getTile(i, j).getBounds()) && map.getTile(i, j).getOptions().equals("shotable"))
					{
						System.out.println("Tile " + i + " " + j + " "+i*j+" " +map.getTile(i, j).getBounds().x + " " + map.getTile(i, j).getBounds().y + " bullets  " + bullets.get(k).getPosition().x + "  "+ bullets.get(k).getPosition().y + " " +bullets.get(k).getBounds().width + " " + bullets.get(k).getBounds().height);
						server.broadcast(new PacketAnimation(new Vector2(map.getTile(i, j).getBounds().x,map.getTile(i, j).getBounds().y),1));
						map.getTile(i, j).setType(1); // ground
						//System.out.println(bullets.get(k).getID());
						players.setScore(bullets.get(k).getID(), 10);
						bullets.remove(k);
						toSend.add(new Vector2(i, j));
						break;
						
					}else if(bullets.get(k).getBounds().overlaps(map.getTile(i, j).getBounds()) && map.getTile(i, j).getOptions().equals("block"))
					{
						bullets.remove(k);
						break;
					}
				}
			}
		}

	
		
		
		for(int i=0 ; i<toSend.size() ; i++)
		{
	server.broadcast(new
		PacketTileUpdate(map.getTile((int)(toSend.get(i).x),(int)(toSend.get(i).y)),(int)(toSend.get(i).x),(int)(toSend.get(i).y)));
		}
	}
	
	private Vector2 possibleRespawn(MapGame map,Players players,int id) {
		System.out.println("possible " + map.getSpawn().size());

		Vector2 pos = map.getSpawnPosition(random());
				
		for(int i=0; i < players.size(); i++)
		{
			if (!players.getPlayerBounds(i).overlaps(new Rectangle(pos.x, pos.y, 32, 32)))		
				{
					return pos;
				}
			pos = map.getSpawnPosition(random());
		}
		return  map.getSpawnPosition(random());
	
	}
	
	private int random()
	{
		Random rand = new Random();
	return 	rand.nextInt(respawnPoints.size());
	}
	private boolean isCollide(Vector2 bullet,Rectangle map)
	{
		if(bullet.x> map.x && bullet.x < map.x+ map.width  
				&& bullet.y > map.y && bullet.y < map.y + map.height)
		{
			return true;
		}
		
		return false;
	}

	public boolean isShoot() {
		return isShoot;
	}

	public ArrayList<BulletMulti> getBullets() {
		return bullets;
	}

	public Vector2 getPosition(int id) {
		return bullets.get(id).getPosition();
	}

	public direction getDirection(int id) {
		return bullets.get(id).getDirection();
	}

	public void add(BulletMulti bullet) {
		// TODO Auto-generated method stub
		if (!isHear(bullet.getID())) {
			System.out.println("Nie ma go, dodaje");
			isShoot = true;
			bullets.add(bullet);
		}
	}
}
