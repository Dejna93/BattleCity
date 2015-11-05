package handlers;

import java.util.ArrayList;
import java.util.Random;

import Tiles.Tile;
import Utils.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.Bullet;
import entities.Tank;

public class EnemySpawner {
	
	private int level;
	private int tankToSpawn;
	private SpriteBatch sb;
	private ArrayList<Vector2>enemySettings;
	ArrayList<Tank> tanks = new ArrayList<Tank>();
	private ArrayList<Vector2> spawnPoints = new ArrayList<Vector2>();
	Rectangle playerBounds;
	
	public void setSprite(SpriteBatch sb)
	{
		this.sb = sb;
	}
	public EnemySpawner(int level,Rectangle playerBounds,SpriteBatch sb,ArrayList<Vector2> spawnPoints,ArrayList<Vector2>enemySettings) {
		// TODO Auto-generated constructor stub
		this.sb = sb;
		this.level = level;
		this.playerBounds = playerBounds;
		this.spawnPoints = spawnPoints;
		this.enemySettings = enemySettings;
		tankToSpawn = (int) enemySettings.get(0).y;
		spawn(5);
	}
	public int size()
	{
		return tanks.size();
	}
	public ArrayList<Rectangle> getTanks()
	{
		ArrayList<Rectangle> rTanks = new ArrayList<Rectangle>();
		for(int i=0; i < tanks.size() ; i++)
		{

				rTanks.add(new Rectangle(tanks.get(i).getBounds()));
		}
		return rTanks;
	}

	public ArrayList<Bullet> getBulletTank(int id)
	{
		return tanks.get(id).getTankBullets();
	}
	public void removeBulletTank(int iT,int iB)
	{
		tanks.get(iT).getTankBullets().remove(iB);
	}
	public void spawn(int count)
	{
		for (int i = 0 ; i < spawnPoints.size() ; i++)
		{
			tankToSpawn--;
			if(i < spawnPoints.size())
				tanks.add(new Tank(playerBounds, spawnPoints.get(i)));
		}
	}
	
	public void update(Rectangle playerBounds, Vector2 position,Tile [][]tMap)
	{
		for (int i=0 ; i < tanks.size() ; i++)
		{
		
			tanks.get(i).updateMap(tMap);
			tanks.get(i).update(position,playerBounds,getList(i));
		
		}
		if(tanks.size()<5)
		spawn(tankToSpawn);
		
	}
	public void remove(int id)
	{
			System.out.println("usuwam");
			tanks.remove(id);
			tankToSpawn--;
	
	}
	private ArrayList<TankUtil> getList(int id)
	{
		ArrayList<TankUtil> tank = new ArrayList<TankUtil>();
		for(int i=0 ; i < tanks.size(); i++)
		{
			if(id!=i)
			{

				tank.add(new TankUtil(tanks.get(i).getBounds(),tanks.get(i).getDirection() ));
			}
		}
		return tank;
	}
	private Vector2 getVector()
	{
		Vector2 position = new Vector2();
		Random rand = new Random();
		position.x = rand.nextInt(640);
		position.y = rand.nextInt(480);
		System.out.println(position.x + " " + position.y);
		return position;
	}
	
	public void draw()
	{
		for(int i=0; i < tanks.size();i++)
		{
			tanks.get(i).getSprite().draw(sb);
			tanks.get(i).drawBullets(sb);
		}

	}
	
}
