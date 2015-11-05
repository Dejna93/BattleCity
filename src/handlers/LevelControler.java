package handlers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import XMLPraser.LoaderLevels;

public class LevelControler  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5617765428781032154L;
	
	
	private int levelCount = 0;
	private ArrayList<String> loadedLevel = new ArrayList<String>();
	public LevelControler() {
		// TODO Auto-generated constructor stub
		try {
			
			LoaderLevels levels = new LoaderLevels("levels/level.xml");
			loadedLevel = levels.getLevels();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getLevel()
	{
		System.out.println(levelCount +  " ");
		if(levelCount<=loadedLevel.size())
			return loadedLevel.get(levelCount);
		else
		{
			levelCount = 0;
			return loadedLevel.get(levelCount);
		}
	}
	public String getNameCurrentLevel()
	{
		return loadedLevel.get(levelCount);
	}
	public int getCount()
	{
		return levelCount;
	}
	public void update()
	{
			levelCount++;
			if(levelCount>loadedLevel.size())
				levelCount=0;
			System.out.println(levelCount + " " + loadedLevel.get(levelCount));
	
	}
	
}
