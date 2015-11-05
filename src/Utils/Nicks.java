package Utils;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import entities.EnemyMulti;
import entities.PlayerMulti;

public class Nicks {
	
	private String username;
	private Vector2 position = new Vector2();
	public Nicks(String username,Vector2 position) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.position = position;
	}
	public Nicks(PlayerMulti player)
	{
		this.username = player.getNick();
		this.position = player.getPosition();
	}
	public Nicks(EnemyMulti enemy)
	{
		this.username = enemy.getNick();
		this.position = enemy.getPosition();
	}
	
	public Nicks getNick()
	{
		return new Nicks(username, position);
	}
	public String getUsername()
	{
		return username;
	}
	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
	public Vector2 getPosition()
	{
		return position;
	}
	public float getX()
	{
		return position.x;
	}
	public float getY()
	{
		return position.y;
	}
	
}
