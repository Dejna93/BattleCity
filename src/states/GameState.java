package states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import handlers.GameStateManager;
import main.Game;

public abstract class GameState {

	protected GameStateManager gSManager;
	protected Game game;
	protected OrthogonalTiledMapRenderer camMap;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	float WIDTH = Gdx.graphics.getWidth();
	float HEIGHT = Gdx.graphics.getHeight();
	protected GameState(GameStateManager gSManager)
	{
		this.gSManager = gSManager;
		game = gSManager.game();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		
	}
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void draw();
	public abstract void dispose();

}
