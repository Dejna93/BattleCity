package states;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL45;

import handlers.C;
import handlers.Collision;
import handlers.EnemySpawner;
import handlers.GameStateManager;
import Animation.Explosion;
import Animation.Water;
import AssetsLoader.Assets;
import GamePlay.SingleGamePlay;
import GuiManager.Gui;
import GuiManager.GuiScore;
import Tiles.Tile;
import Utils.Direction.direction;
import XMLPraser.ReaderXML;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import entities.Bullet;
import entities.Player;
import entities.Tank;

public class PlaySingle extends GameState implements Screen {

	private OrthographicCamera cam;

	private Player player;
	private boolean hit = false;
	private ShapeRenderer sR;
	private ArrayList<Tank> aTanks;
	private ArrayList<Vector2> spawnPoints = new ArrayList<Vector2>();
	private EnemySpawner enemySpawner;

	private Sprite panel;
	private TextureAtlas atlas;
	private SingleGamePlay gamePlay;

	private ReaderXML map = new ReaderXML("levels/level1.xml");

	private Water water;
	
	private Tile[][] tMap = new Tile[20][15];

	private Explosion explosion;

	private Gui gui;
	
	// --
	Collision collision;
	public PlaySingle(GameStateManager gSManager) {
		super(gSManager);
		// gen();
		try {
			for(int i=0; i<20;i++)
			{
				for(int j=0; j <15;j++)
				{
					System.out.print(" Tile "+i*32 + " " + j*32+ " / " );
				}
				System.out.println("");
			}
			aTanks = new ArrayList<Tank>();
			
			explosion = new Explosion();
			cam = new OrthographicCamera();
			cam.setToOrtho(false, WIDTH, HEIGHT);
			cam.update();
			sR = new ShapeRenderer();
			
	

			tMap = map.loader(true);
			spawnPoints = map.getSpawnPoints();
			player = new Player(tMap);
			gamePlay = new SingleGamePlay(player);
			gui= new Gui(false);
	
			enemySpawner = new EnemySpawner(1, player.getBounds(), sb,spawnPoints,map.getEnemySettings());

		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			gSManager.popState();
			gSManager.pushState(gSManager.MENU);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			player.setMove(direction.Up);
			// player.setUp();
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			// player.setDown();
			player.setMove(direction.Down);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			// player.setLeft();
			player.setMove(direction.Left);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.setMove(direction.Right);
			// player.setRight();
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) // && lastShoot > 0.2f)
		{
			if (!player.isShooting()) {
				player.shoot();
				player.setShoot(true);
			}
			// System.out.println("shooting");
			// shoot.shoot(player.getPosition(),player.getPlayerDirect());
			// lastShoot = 0f;
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		//Collision coll = new Collision(player.getPosition().x, player.getPosition().y , tMap);
	//	System.out.println(coll.isCollideLeft());
		handleInput();

		player.update(tMap, enemySpawner.getTanks());

		//collision = new CollisionMultiplayer(player.getPosition().x, player.getPosition().y,tMap);
		
	//	System.out.println(collision.isCollideLeft() + " " + collision.isCollideRight() + "  "+ collision.isCollideTop() + " " + collision.isCollideBot());

	
		enemySpawner.update(player.getBounds(), player.getPosition(), tMap);
	
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 15; y++) {
				for (int j = 0; j < player.bulletsize(); j++) {
					if (tMap[x][y].getBounds().overlaps(player.getBoundsBullet(j))&& tMap[x][y].getOptions().equals("shotable")){
						explosion.update(true,tMap[x][y].getOrigin(),dt);
						player.removeBullet(j);
						tMap[x][y].updateType(1);
				
						break;
					}else if (tMap[x][y].getBounds().overlaps(player.getBoundsBullet(j))&& tMap[x][y].getOptions().equals("block"))
					{
						player.removeBullet(j);
					}
					}
				for(int i=0 ; i < enemySpawner.size() ; i++)
				{
					ArrayList<Bullet>btank = enemySpawner.getBulletTank(i);
					for(int j = 0 ; j < btank.size() ; j++)
					{
						if (player.getBounds().overlaps(btank.get(j).getBounds()))
						{
							//enemySpawner.removeBulletTank(i, j);
							explosion.update(true,player.getPosition(),dt);
						}
						if (tMap[x][y].getBounds().overlaps(btank.get(j).getBounds()) && tMap[x][y].getOptions().equals("shotable"))
						{
							explosion.update(true,tMap[x][y].getOrigin(),dt);
							tMap[x][y].updateType(1);
							
							enemySpawner.removeBulletTank(i, j);
							break;
						}
						else if (tMap[x][y].getBounds().overlaps(btank.get(j).getBounds()) && tMap[x][y].getOptions().equals("block"))
						{
							enemySpawner.removeBulletTank(i, j);
							break;
						}
						
						
					}
				//	if(tMap[x][y].getBounds().overlaps(enemySpawner.getBulletTank(i)))
				}
			}	
		}

		gamePlay.update(enemySpawner);
		// System.out.println(player.getBounds().x/layer.getTileWidth()+ " "+
		// player.getBounds().y/layer.getTileHeight());

	}

	@Override
	public void render() {

		cam.update();


		draw();

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

		sb.begin();

		sb.enableBlending();
		
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 15; j++) {
				if (player.getBounds().overlaps(tMap[i][j].getBounds())
						&& tMap[i][j].getOptions().equals("hide")) {

					tMap[i][j].draw(sb);

				} else {
					tMap[i][j].draw(sb);
				}

			}
		}
		
		// for (int i=0; i< tileMap.size(); i++)
		// {
		// System.out.println(tileMap.get(i).getBounds().x + " "
		// +tileMap.get(i).getBounds().y + " " +
		// tileMap.get(i).getBounds().width + " " +
		// tileMap.get(i).getBounds().height) ;
		// tileMap.get(i).draw(sb);
		// }
		for (int i = 0; i < aTanks.size(); i++) {
			aTanks.get(i).getSprite().draw(sb);
			aTanks.get(i).drawBullets(sb);
		}

		player.drawBullets(sb);
		player.getSB().draw(sb);
		explosion.draw(sb);
		enemySpawner.draw();
		
		gui.draw(sb);
		sb.end();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private int random() {
		Random rand = new Random();
		return rand.nextInt(500);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
}
