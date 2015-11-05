package GamePlay;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.Sys;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import handlers.DatebaseHandler;
import Packet.PacketBullets;
import Packet.PacketDisconnect;
import Packet.PacketLoged;
import Packet.PacketLogin;
import Packet.PacketMessage;
import Packet.PacketMove;
import Packet.PacketRegister;
import Packet.PacketRegisterSuccesful;
import Packet.PacketRequest;
import Packet.PacketReset;

import Packet.PacketRetryGame;

import Packet.PacketShoot;
import Packet.PacketTopScore;
import Packet.PacketUpdate;
import Packet.PacketWiner;
import ServerGame.MapGame;
import ServerGame.Players;
import ServerGame.ScoreBoard;
import ServerGame.Server;
import ServerGame.ShootManager;
import Utils.MapFromServer;
import Utils.Message;

import Utils.StateMulti;
import Utils.StateMulti.State;

public class MultiGamePlay {

	private ScoreBoard scoreBoard;
	private Players playersConnected;
	private MapGame map;
	private ShootManager shootManager;
	private DatebaseHandler db;

	private ControllGame controlGame;
	private Server server;

	private StateMulti stateGame;
	private boolean isSend = false;

	private ArrayList<Vector2> respawnPoints;

	public MultiGamePlay(Server server) {
		// TODO Auto-generated constructor stub

		this.server = server;
		controlGame = new ControllGame(30);
		scoreBoard = new ScoreBoard();
		playersConnected = new Players(scoreBoard);
		map = new MapGame();
		respawnPoints = map.getSpawn();
		shootManager = new ShootManager(scoreBoard, db, respawnPoints);
		db = new DatebaseHandler();
		stateGame = new StateMulti();

		;
	}

	public String getTime() {
		return controlGame.getServerTime();
	}

	public void setTourTime(long time)
	{
		controlGame.setTourTime(time);
	}
	public String getCurrentNameLevel()
	{
		return map.getCurrentNameLevel();
	}
	public void update() {

		// System.out.println(playersConnected.size());
		if (controlGame.update(playersConnected)) {

			isSend = false;

			stateGame.setState(State.PLAYING);
			shootManager.update(map, server, playersConnected, respawnPoints);

			if (shootManager.isShoot() == true) {

				server.broadcast(new PacketBullets(shootManager));
			}

			scoreBoard = playersConnected.getUpdateScore();

			server.broadcast(new PacketUpdate(playersConnected, scoreBoard,
					new Message(false), controlGame.getTime(), stateGame));
		} else if (controlGame.getReset() == true) {
			// System.out.println("reset " + " " + controlGame.getTime());
			stateGame.setState(State.RESETING);
			// server.broadcast(new PacketResetMsg("Reseting game", true,
			// true));
			checkwhoWin();
			if (isSend == false) {

				scoreBoard.sendScoreToDB(db, playersConnected.getUpdateScore());

				scoreBoard.reset();
				map.resetMap();
				playersConnected.respawnPlayers(respawnPoints);

				server.broadcast(new PacketReset(
						new MapFromServer(map.getMap()), playersConnected,
						scoreBoard, db.getTopScore(), stateGame));
				isSend = true;
			}

		} else if (controlGame.getReset() == false
				&& controlGame.update(playersConnected) == false) {
			// System.out.println("waiting " + " " + controlGame.getTime());
			stateGame.setState(State.WAITING);

			server.broadcast(new PacketMessage("Waiting for players", true));
			server.broadcast(new PacketUpdate(playersConnected, scoreBoard,
					new Message("Waiting for players", true), controlGame
							.getTime(), stateGame));
		}
	}

	public ShootManager getShootManager() {
		return shootManager;
	}

	public Server getServer() {
		return server;
	}

	public Players getPlayers() {
		return playersConnected;
	}

	public void checkwhoWin() {
		int idwiner = 0;
		int max = 0;

		for (int i = 0; i < playersConnected.size(); i++) {
			if (playersConnected.getPlayer(i).getScore().getIScore() > max) {
				max = playersConnected.getPlayer(i).getScore().getIScore();
				idwiner = i;
			}
		}
		server.broadcast(new PacketWiner(playersConnected.getPlayer(idwiner)
				.getUsername()));
	}

	// HANDLERS PACKETS

	public void handleMove(Object obj, int port) {
		// TODO Auto-generated method stub
		// if (playersConnected.size() > 1) {
		if (stateGame.getState() == State.PLAYING) {
			PacketMove packet = (PacketMove) obj;

			playersConnected.update(getID(port), packet.getPosition(), map);
			for (int i = 0; i < playersConnected.size(); i++) {
				System.out.println("Klient  " + i + " "
						+ playersConnected.getPlayers().get(i).getID() + " "
						+ playersConnected.getPlayers().get(i).getPosition().x
						+ " "
						+ playersConnected.getPlayers().get(i).getPosition().y);
			}
			// server.broadcast(new
			// PacketUpdate(playersConnected.getPlayers()));
			// }
		}
	}

	public void handleLogin(PacketLogin packetLogin, int port) {
		System.err.println(packetLogin.getID() + " "
				+ packetLogin.getUsername() + " " + packetLogin.getPassword());

		
		if (db.isContain(packetLogin.getUsername(), packetLogin.getPassword()) && !isLogin(packetLogin.getUsername())) {
			System.out.println("zalogowany" + " send ");
			playersConnected.add(port, packetLogin.getUsername(),
					possibleRespawn(map)); // do testow
			server.getClients()
					.get(getID(port))
					.send(new PacketLoged(getID(port), true, playersConnected
							.getPlayers(), new MapFromServer(map.getMap())));
		} else {
			System.out.println("blad ");
			try {
				System.out.println(server.getClients().size() + " "
						+ getID(port) + playersConnected.size());
				server.getClients().get(getID(port))
						.send(new PacketLoged(false));
				System.out.println(server.getClients().size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private Vector2 possibleRespawn(MapGame map) {
		for (int j = 0; j < map.getSpawn().size(); j++) {
			for (int i = 0; i < playersConnected.size(); i++) {
		
				if (!playersConnected.getPlayerBounds(i).overlaps(
						new Rectangle(map.getSpawnPosition(j).x, map
								.getSpawnPosition(j).y, 32, 32)))
					
				{
					return map.getSpawnPosition(j);
				}
			}
		}
		return  map.getSpawnPosition(random());
	

	}

	private boolean isLogin(String username)
	{
		for(int i=0; i < playersConnected.size() ; i++)
		{
			if(playersConnected.getPlayer(i).getUsername().equals(username))
			{
				return true;
			}
		}
		return false;
	}
	private int random() {
		Random rand = new Random();
		return rand.nextInt(respawnPoints.size());
	}

	public void handleRegister(PacketRegister packet, int port) {
		if (db.registerSQl(packet.getUsername(), packet.getPassword())) {
			server.getClients().get(getID(port))
					.send(new PacketRegisterSuccesful(true));
		} else {
			server.getClients().get(getID(port))
					.send(new PacketRegisterSuccesful(false));
		}
	}

	public void handleDisconnect(PacketDisconnect packet, int port) {
		if (packet.getClose()) {
			server.remove(port);
			scoreBoard.remove(getID(port));
			playersConnected.remove(getID(port));
		}
	}

	public ScoreBoard getScoreBoard() {
		return scoreBoard;
	}

	public void handleShoot(PacketShoot packet) {
		shootManager.add(packet.getBullet());

	}

	private int getID(int port) {
		for (int i = 0; i < server.clientCount; i++)
			if (server.getClients().get(i).getPort() == port)
				return i;
		System.err.println("Port nie znaleziony kod " + "-1");
		return -1;
	}

	public void handleRetry(PacketRetryGame packet) {
		System.out.println(packet.getReset() + " " + packet.getPort());
		int id = getID(packet.getPort());
		playersConnected.getPlayer(id).setRetry(packet.getReset());
	}

	public void handleRequestDB(PacketRequest packet, int port) {
		// TODO Auto-generated method stub
		System.out.println("handle requet");
		server.broadcast(new PacketTopScore(db.getTopScore()));
	}

}
