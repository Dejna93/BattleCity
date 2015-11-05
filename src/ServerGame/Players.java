package ServerGame;

import java.util.ArrayList;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



public class Players {
	
	private ArrayList<PlayerData> players = new ArrayList<PlayerData>();
	private MapGame map = new MapGame();
	private ScoreBoard scoreBoard =new ScoreBoard();

	public Players(ScoreBoard scoreBoard) { 
		this.scoreBoard = scoreBoard;
	}
	public Players(ArrayList<PlayerData> newplayers)
	{
		players = new ArrayList<PlayerData>(newplayers);
	}
	
	public void add(int ID,String username,Vector2 pos)
	{
		players.add(new PlayerData(ID,username,pos));
		
		scoreBoard.addPlayer(new PlayerData(ID,username,pos));
		
	}
	public void update(int port, Vector2 move, MapGame map)
	{
		this.map = map;
		for (int i =0 ; i < players.size(); i++)
		{		
			Rectangle futurePosition = new Rectangle(players.get(port).getPosition().x + move.x , players.get(port).getPosition().y + move.y,32,32);
			if (futurePosition.overlaps(players.get(i).getBounds()) && port!=i)
			{
				System.out.println("Kolizja");
				move = new Vector2(0,0);
				break;
			}
		}
		for (int i=0 ; i < 20; i++)
		{
			for(int j=0;j< 15; j++)
			{
			Rectangle futurePosition = new Rectangle(players.get(port).getPosition().x + move.x , players.get(port).getPosition().y + move.y,32,32);
				if (futurePosition.overlaps(map.getTile(i,j).getBounds()) && (map.getTile(i,j).getOptions().equals("block") || map.getTile(i,j).getOptions().equals("shotable")) )
				{
					System.out.println("Kolizja");

					players.get(port).setDirection(move);				
					move = new Vector2(0,0);
					break;
				}
			}
		}
		
		
		players.get(port).move(move);
	
	}
	
	public void respawnPlayers(ArrayList<Vector2> respawnPoints)
	{
		for(int i=0; i < players.size(); i++)
		{
			players.get(i).respawn(respawnPoints.get(i));
		}
	}
	public void setScore(int bulletID,int score)
	{
		for(int i=0; i < players.size();i++){
			if(players.get(i).getID() == bulletID)
			{
				players.get(i).getScore().updateScore(score);
				scoreBoard.updateScore(players.get(i).getScore());
			}
		}
	}
	public ScoreBoard getUpdateScore()
	{
		return scoreBoard;
	}
	public int size()
	{
		return players.size();
	}
	public PlayerData getPlayer(int id)
	{
		return players.get(id);
	}
	public ArrayList<PlayerData> getPlayers()
	{
			return players;
	}
	public Rectangle getPlayerBounds(int id)
	{
		return players.get(id).getBounds();
	}
	public Vector2 getPlayerPosition(int ID)
	{
		if( ID <players.size())
			return players.get(ID).getPosition();
		else
			return null;
	}
	public int getPlayerID(int i)
	{
		return players.get(i).getID();
	}

	public void remove(int ID) {
		// TODO Auto-generated method stub
		players.remove(ID);
		System.err.println("usuniety player"+ ID + players.size());
	}
}
