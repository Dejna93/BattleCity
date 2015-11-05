package states;

import handlers.ServerStateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ServerGame.ServerApp;

public abstract class ServerState {

	protected ServerStateManager sStateManager;
	protected ServerApp serverApp;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	float WIDTH = Gdx.graphics.getWidth();
	float HEIGHT = Gdx.graphics.getHeight();
	
	protected ServerState(ServerStateManager sStateManager) {
		this.sStateManager = sStateManager;
		serverApp = sStateManager.getServerApp();
		sb = serverApp.getSprite();
		cam = serverApp.getCam();
	}
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void draw();
	public abstract void dispose();
}
