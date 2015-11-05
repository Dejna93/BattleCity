package ServerGame;

import main.Game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class ServerGui {

	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Game.TITLE;
		cfg.width = Game.WIDTH ;
		cfg.height = Game.HEIGHT;
		//cfg.resizable = false;
		
		new LwjglApplication(new ServerApp(), cfg);
		
	}
}
