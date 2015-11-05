package main;



import handlers.GameStateManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Game implements ApplicationListener{

	//---------------INITIAL SETTINGS
	public static final String TITLE = "Battle City";
	public static final int WIDTH = 800;
	public static final int HEIGHT  = 480;
	public static final float STEP = 1 / 60f;
	
	//---------------
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	
	private GameStateManager gSManager;
	
	public SpriteBatch getSpriteBatch()
	{
		return sb;
	}
	public OrthographicCamera getCamera()
	{
		return cam;
	}

	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		cam = new OrthographicCamera();
		cam.setToOrtho(false,WIDTH,HEIGHT);
		
		sb = new SpriteBatch();
		gSManager = new GameStateManager(this);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.graphics.setTitle(TITLE); 
		gSManager.update(Gdx.graphics.getDeltaTime());  //kontrola fpsów
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);
		gSManager.render();
	//	camMap.setView(cam);
	//	camMap.render();
		
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
