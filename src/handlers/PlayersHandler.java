package handlers;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import entities.EnemyMulti;

import ServerGame.PlayerData;

public class PlayersHandler {

	private ArrayList<EnemyMulti> enemies;
	private int enemiesCount = 0;	
	public PlayersHandler() {
		// TODO Auto-generated constructor stub
		enemies = new ArrayList<EnemyMulti>();
	}
	public PlayersHandler(ArrayList<PlayerData> players,int ID) {
		// TODO Auto-generated constructor stub
		enemies = new ArrayList<EnemyMulti>();
			for (int i = 0 ; i < players.size() ; i++)
			{
				if(i != ID)
				{
				enemies.add(new EnemyMulti(players.get(i)));
				enemiesCount++;
				}
				
			}
		
	}
	public void update(ArrayList<PlayerData> players,int ID)
	{		
		enemies = new ArrayList<EnemyMulti>();
		for (int i =0 ; i < players.size() ; i++)
		{
			if(i != ID)
			{
			
			enemies.add(new EnemyMulti(players.get(i)));
			// System.err.println(i +  " " + enemies.get(i).getPosition().x + " " + enemies.get(i).getPosition().y );
			enemiesCount++;
			}
		}
	}
	public EnemyMulti getEnemy(int id)
	{
		return enemies.get(id);
	}

	
	public int size()
	{
		return enemies.size();
	}
	public void draw(SpriteBatch sb)
	{
		
		if ( enemiesCount > 0)
		{
			for (int i = 0 ; i < enemies.size() ; i++)
			{
				enemies.get(i).getSprite().draw(sb);
			//	System.out.println("Wrog "+i+ " "+ enemies.get(i).getPosition().x + " " +enemies.get(i).getPosition().y );
				
			}
		}
	}
}
