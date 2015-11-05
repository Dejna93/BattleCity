package AssetsLoader;

import java.util.ArrayList;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Assets {
	private static final AssetManager manager = new AssetManager();
	private static ArrayList<String> assetsMap = new ArrayList<String>();
	private static void create()
	{
		try{
			assetsMap.add("assets/texture/player_up.png");
			assetsMap.add("assets/texture/enemy_tank_1.png");
			assetsMap.add("assets/texture/shoot.png");
			assetsMap.add("assets/texture/bullet.png");
			assetsMap.add("assets/texture/menu.png");
			assetsMap.add("assets/json/uiskin.json");
			assetsMap.add("levels/tileset.pack");
			assetsMap.add("levels/tileset.png");
			assetsMap.add("assets/tile/none.png");
			assetsMap.add("assets/tile/brick.png");
			assetsMap.add("assets/tile/stone.png");
			assetsMap.add("assets/tile/grass.png");
			assetsMap.add("assets/tile/water.png");
			assetsMap.add("assets/tile/herb.png");
			assetsMap.add("assets/animation/explosion.atlas");
			assetsMap.add("assets/animation/respawn/respawn.atlas");
			assetsMap.add("assets/texture/panel.png");
			assetsMap.add("assets/texture/enemy.png");
			assetsMap.add("assets/texture/winerpanel.png");
			assetsMap.add("assets/texture/background.png");
			assetsMap.add("assets/animation/water/water.atlas");
			//test  do obejzenia loading bara
			assetsMap.add("assets/test.wav");
			assetsMap.add("assets/test1.wav");
			assetsMap.add("assets/test2.wav");
			assetsMap.add("assets/test3.wav");
			//assetsMap.add("assets/test4.wav");
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	public static void load(String assets)
	{
		try{
			if(assets!="")
					manager.load(assets,getType(getExtension(assets)));
			else
				System.err.println("Empty filepath to assets");
				}catch(Exception e)
				{
					e.printStackTrace();
				}

	}
	public static void loader()
	{
		create();
		for (int i=0; i < assetsMap.size() ; i++)
		{
			load(assetsMap.get(i));
		}
	}
	public static AssetManager getManager()
	{
		return manager;
	}
	
	private static <T> Class getType(String filetype) {
		switch (filetype) {
		case "png":
				return  Texture.class;
		case "pack":
				return TextureAtlas.class;
		case "json":
				return Skin.class;	
		case "atlas":
			return TextureAtlas.class;
		case "wav":
				return Sound.class;
		default:
			break;
		}
		return null;

	 }
	 private static String getExtension(String filepath)
	 {
		 String tmp = "";
		 int i = filepath.lastIndexOf('.');
		 if (i > 0)
		 {
			 tmp = filepath.substring(i+1);
		 }
		 return tmp;
	 }
	public static boolean isLoaded()
	{
		if (manager.getProgress() >=1)
			return true;
		return false;
	}
	public static void dispose()
	{
		manager.dispose();
	}
	
}
