package handlers;

import java.util.ArrayList;

import Utils.Nicks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entities.PlayerMulti;

public class NickDrawer {

	private SpriteBatch sb;
	
	private ArrayList<Nicks> nicksList;
	
	private BitmapFont font;
	public NickDrawer() {
		// TODO Auto-generated constructor stub

		nicksList = new ArrayList<Nicks>();
		font = new BitmapFont(Gdx.files.internal("assets/font/font.fnt"),Gdx.files.internal("assets/font/font.png"),false);
		font.setScale(0.6f);
	}
	public void update(ArrayList<Nicks> nicks)
	{
		nicksList = nicks;

	}
	
	
	private void contain(Nicks nick)
	{
		boolean isContain = false;
		for(int i=0; i < nicksList.size() ; i++)
		{
			if(nicksList.get(i).getUsername().equals(nick.getUsername()))
			{
			//	System.out.println("zawiera sie");
				nicksList.get(i).setPosition(nick.getPosition());
				isContain = true;
			}
		}
		if(isContain==false)
			nicksList.add(nick);

	}
	public void draw(SpriteBatch sb)
	{

		this.sb  =sb;
	//	System.out.println("size " +nicksList.size())
		try{
		for(int i=0; i < nicksList.size() ; i++)
			
		{
			font.draw(sb, nicksList.get(i).getUsername(), nicksList.get(i).getX(),  nicksList.get(i).getY()+52);
		}
		}catch (Exception e )
		{
			e.printStackTrace();
		}
	}
}
