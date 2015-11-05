package handlers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Packet.PacketBullets;
import Utils.BulletsSprite;

public class BulletsHandler {

	private ArrayList<BulletsSprite> bulletsSprite;
	
	public BulletsHandler() {
		// TODO Auto-generated constructor stub
	}
	public void add(PacketBullets packet)
	{
		bulletsSprite= new ArrayList<BulletsSprite>();
		
		for (int i=0; i < packet.size(); i++)
		{
			bulletsSprite.add(new BulletsSprite(packet.getPosition(i), packet.getDirection(i)));
		//	System.out.println(" BulletsSprite " + packet.getPosition(i).x + " " + packet.getPosition(i).y);
		}
	}
	public void update(PacketBullets packet) {
		// TODO Auto-generated method stub
		bulletsSprite= new ArrayList<BulletsSprite>();
		
		for (int i=0; i < packet.size(); i++)
		{
			bulletsSprite.add(new BulletsSprite(packet.getPosition(i), packet.getDirection(i)));
		//	System.out.println(" BulletsSprite " + packet.getPosition(i).x + " " + packet.getPosition(i).y);
		}
	}
	
	public void update(BulletsHandler bullets)
	{
		this.bulletsSprite = bullets.getBulletSprite();
	}
	
	private ArrayList<BulletsSprite> getBulletSprite()
	{
		return bulletsSprite;
	}
	public void draw(SpriteBatch sb)
	{
	if(bulletsSprite!= null)
		for(int i=0; i < bulletsSprite.size();i++)
		{
			bulletsSprite.get(i).getSprite().draw(sb);
		}
	}
}
