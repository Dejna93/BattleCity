package states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import Animation.Explosion;
import Animation.Respawn;
import Animation.Water;
import DialogPopUp.DialogDisconnect;
import DialogPopUp.DialogTopScore;
import GuiManager.Gui;
import GuiManager.GuiWiner;
import GuiManager.ServerMessage;
import ServerGame.ScoreBoard;
import Tiles.Tile;
import Utils.StateMulti;
import Utils.StateMulti.State;
import Utils.TopScore;
import entities.BulletMulti;
import entities.PlayerMulti;
import handlers.BulletsHandler;
import handlers.GameStateManager;
import handlers.MapHandler;
import handlers.NickDrawer;
import handlers.PlayersHandler;
import network.Connection;

public class MultiplayerState extends GameState {

	private OrthographicCamera cam;

	private Connection connection;
	// private ArrayList<PlayerMulti> players = new ArrayList<PlayerMulti>();
	private PlayerMulti player;
	private ArrayList<Tile> tileMap = new ArrayList<Tile>();
	private PlayersHandler enemies = new PlayersHandler();
	private BulletsHandler bullets = new BulletsHandler();
	private DialogTopScore dialogTopScore;
	private DialogDisconnect dialogDisconnect;
	private Skin skin;
	private Stage stage;

	private NickDrawer nickDraw;
	private MapHandler map;
	private ServerMessage sMessage;
	private long startTime;
	private Gui guiManager;
	private GuiWiner guiWiner;
	private StateMulti stateGame = new StateMulti();
	private Explosion explosion = new Explosion();
	private Water waterAnim;
	private Respawn respawnAnim;
	float shootTime = 0;
	float shootDelay = 1f;

	public MultiplayerState(GameStateManager gSManager) {
		super(gSManager);
		// TODO Auto-generated constructor stub

	}

	public MultiplayerState(GameStateManager gSManager, Connection connection) { // for
																					// login
		super(gSManager);

		stage = new Stage();
		skin = new Skin(Gdx.files.internal("assets/json/uiskin.json"));
		Gdx.input.setInputProcessor(stage);
		startTime = System.currentTimeMillis();
		// TODO Auto-generated constructor stub
		this.connection = connection;
		// enemy.setPosition(400, 400);
		// enemies = new PlayersHandler(connection.getPlayers(),
		// connection.getPort());
		player = new PlayerMulti();
		guiWiner = new GuiWiner(100, 50);
		dialogDisconnect = new DialogDisconnect("Do you wanna disconnect?",
				skin, connection, gSManager);
		try {
			dialogTopScore = new DialogTopScore("TOP SCORE", skin, connection,
					gSManager);
			dialogTopScore.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map = new MapHandler(connection.getMap());
		waterAnim = new Water(map.getWaterTiles());
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		cam.update();
		cam.position.set(WIDTH / 2, HEIGHT / 2, 0);

		respawnAnim = new Respawn();
		nickDraw = new NickDrawer();
		guiManager = new Gui(true, skin, stage, connection, gSManager);
		sMessage = new ServerMessage();
		// enemies = new PlayersHandler(connection.getPlayers(),
		// connection.getID());

	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		/*
		 * if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
		 * System.out.println("koniec"); connection.sendDisconnect(true);
		 * gSManager.popState(); gSManager.pushState(gSManager.MENU); }
		 */

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			connection.sendMove(new Vector2(0, 2));
			// player.move(connection.getMove());
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {

			connection.sendMove(new Vector2(0, -2));

		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			connection.sendMove(new Vector2(-2, 0));

		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {

			connection.sendMove(new Vector2(2, 0));

		}

		if (Gdx.input.isKeyPressed(Keys.SPACE) && shootTime > shootDelay) {
			System.err.println(connection.getPort() + " "
					+ player.getPosition().x + " " + player.getPosition().y);

			connection.sendShoot(new BulletMulti(connection.getPort(), player
					.getPosition(), player.getPlayerDirect()));
			shootDelay += 1f;

		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			dialogDisconnect.show(stage);

		}

	}



	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println(connection.isReset); if (connection.isReset) {
		 * System.out.println(dialogTopScore.toString());
		 * //dialogTopScore.setScore(connection.getTopScore()); TopScore[] top =
		 * connection.getTopScore(); System.out.println(top.toString()); }
		 */
		try {
			if(connection.getState()!=null)
			stateGame = connection.getState();
			else 
			{
				stateGame.setState(State.WAITING);
			}
			if (stateGame.getState() == State.RESETING) {
				
				guiWiner.update(connection.getWinerName());
				System.out.println(connection.getWinerName());
			}
			// if (stateGame.getState() == State.PLAYING) {
			handleInput();
			player.update(connection.getPlayer(connection.getID()));
			explosion.update(connection.getAnimiation());
			respawnAnim.update(connection.getAnimiationResoawn());
			map.update(connection);
			sMessage.update(connection.getServerMessage(),
					connection.getSizeClients());
			waterAnim.update(map.getWaterTiles());
			bullets.update(connection.getBullets());
			// enemies.update(connection.getPlayers(), connection.getPort());
			enemies.update(connection.getPlayers(), connection.getID());

			nickDraw.update(connection.getNicks());
			guiManager.update(connection.getScoreBoard(), connection.getTime());
			cam.update();
		} catch (Exception e) {
			e.printStackTrace();
			stateGame.setState(State.WAITING);
		}
		// }
		// else if (stateGame.getState() == State.RESETING)
		// {
		// System.out.println("RESETING GAME");
		// }
		// else if (stateGame.getState() == State.WAITING)
		// {
		// System.out.println("WAITING FOR PLAYERS");
		// }

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		draw();
		stage.act();
		stage.draw();
		// System.err.println("Time left " +(System.currentTimeMillis() -
		// startTime) / 1000);
		shootTime = (System.currentTimeMillis() - startTime) / 1000;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		sb.begin();
		guiManager.draw(sb);
		map.draw(sb);
		if(waterAnim !=null)
		waterAnim.draw(sb);
		player.getSB().draw(sb);
	
		// enemy.draw(sb);
		enemies.draw(sb);
	
		explosion.draw(sb);
		
		respawnAnim.draw(sb);
		if (stateGame.getState() == State.RESETING) {
			guiWiner.draw(sb);
		}
		if (bullets != null)
			bullets.draw(sb);
		if(stateGame.getState() != State.RESETING)
			nickDraw.draw(sb);
		guiManager.draw(sb);

		sMessage.draw(sb);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		connection.sendDisconnect(true);
	}

}
