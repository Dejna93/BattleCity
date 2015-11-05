package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class BattleCity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Game.TITLE;
		cfg.width = Game.WIDTH ;
		cfg.height = Game.HEIGHT;
		//cfg.resizable = false;
		
		new LwjglApplication(new Game(), cfg);
	}

}
