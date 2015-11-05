package network;

import handlers.BulletsHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;




import Packet.PacketAnimation;
import Packet.PacketBullets;
import Packet.PacketDisconnect;
import Packet.PacketLoged;
import Packet.PacketLogin;
import Packet.PacketMap;
import Packet.PacketMessage;
import Packet.PacketMove;
import Packet.PacketRegister;
import Packet.PacketRegisterSuccesful;
import Packet.PacketRequest;
import Packet.PacketReset;
import Packet.PacketResetMsg;
import Packet.PacketRespawn;
import Packet.PacketRetryGame;
import Packet.PacketScoreTable;
import Packet.PacketShoot;
import Packet.PacketTile;
import Packet.PacketTileUpdate;
import Packet.PacketTopScore;
import Packet.PacketUpdate;
import Packet.PacketWiner;
import Packet.Utils;
import ServerGame.MapGame;
import ServerGame.PlayerData;
import ServerGame.ScoreBoard;
import Tiles.Tile;
import Utils.AnimationType;
import Utils.MapFromServer;
import Utils.Message;
import Utils.Nicks;
import Utils.StateMulti;
import Utils.TopScore;

import com.badlogic.gdx.math.Vector2;

import entities.BulletMulti;
import entities.PlayerMulti;

public class Connection implements Runnable {
	private Socket socket = null;

	private Thread thread = null;
	private ConnectionThread client = null;
	private ObjectInputStream streamIn;
	private ObjectOutputStream streamOut;

	private boolean isLogin = false;
	public boolean isHandling = false;

	public boolean getLoged = false;
	public boolean sendLogin = false;

	public boolean sendRegister = false;
	public boolean isRegister = false;
	
	private int IDClient = -1;

	private ArrayList<PlayerData> players = new ArrayList<PlayerData>();
	private ArrayList<Nicks> playerNicks = new ArrayList<Nicks>();
	private MapFromServer map = new MapFromServer();
	private BulletsHandler bulletHandler = new BulletsHandler();
	private ScoreBoard scoreBoard = new ScoreBoard();
	public boolean isReset;
	private Message serverMessage;
	private long timeGame=0;
	private TopScore []  topScore = new TopScore[10];
	private StateMulti stateGame = new  StateMulti();
	
	private ArrayList<Vector2> exploAnim= new ArrayList<Vector2>();
	private ArrayList<Vector2> respAnim= new ArrayList<Vector2>();
	private String winerName="";
	public Connection() {

	}

	public Connection(String ip, int port) {
		// TODO Auto-generated constructor stub
		try {

			socket = new Socket();
			socket.connect(new InetSocketAddress(ip, port), 1000);
			System.err.println(socket.isConnected());

			open();
		} catch (IOException e) {
			System.err.println(socket.isConnected());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void send(Object object) {
		try {
			streamOut.writeObject(object);
			streamOut.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ObjectInputStream createInputStream(Socket socket)
			throws IOException {
		InputStream stream = socket.getInputStream();
		BufferedInputStream buffer = null;

		try {
			buffer = new BufferedInputStream(stream);

			return new ObjectInputStream(buffer);
		} catch (IOException ex) {
			if (buffer != null)
				Utils.close(buffer);

			stream.close();

			throw ex;
		}
	}

	public ObjectOutputStream createOutputStream(Socket socket)
			throws IOException {
		OutputStream stream = socket.getOutputStream();

		try {
			return new ObjectOutputStream(stream);
		} catch (IOException ex) {
			stream.close();

			throw ex;
		}
	}

	public void open() {
		try {
			streamOut = this.createOutputStream(socket);

			if (thread == null) {
				client = new ConnectionThread(this, socket);
				thread = new Thread(this);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void handle(Object obj) {
		// System.out.println(obj.toString());

		if (obj.getClass() == PacketLoged.class) {
			PacketLoged pack = (PacketLoged) obj;
			// players = pack.getPlayers();
			getLoged = true;
			handleLogin(pack);

		} else if (obj.getClass() == PacketUpdate.class) {
		
			PacketUpdate packet = (PacketUpdate) obj;
			isReset = false;
			players = packet.getPlayers();
			updateNicks(players);
			scoreBoard = packet.getScoreBoard();
			serverMessage = packet.getMessage();
			timeGame = packet.getTime();
			stateGame = packet.getState();
		
			
		} else if (obj.getClass() == PacketMap.class) {
			PacketMap packetMap = (PacketMap) obj;
			map = packetMap.getMap();
		} else if (obj.getClass() == PacketBullets.class) {
			PacketBullets packet = (PacketBullets) obj;

			bulletHandler.add(packet);
		} else if (obj.getClass() == PacketTileUpdate.class) {
			PacketTileUpdate packet = (PacketTileUpdate) obj;
			map.setTile(packet.getBounds(), packet.getType(),
					packet.getOption(), packet.getI(), packet.getJ());

		} else if (obj.getClass() == PacketRegisterSuccesful.class) {
			PacketRegisterSuccesful packet = (PacketRegisterSuccesful) obj;
			isRegister = packet.getState();
		}

		else if (obj.getClass() == PacketResetMsg.class)
		{
		
			PacketResetMsg packet = (PacketResetMsg)obj;
			//System.out.println("reseting " + packet.getReset());
			serverMessage = packet.getMessage();
			isReset = true;
		}
		else if (obj.getClass() == PacketMessage.class)
		{
			PacketMessage packet = (PacketMessage) obj;
	
			serverMessage = packet.getMessage();
		}
		else if (obj.getClass() == PacketReset.class)
		{
		
			PacketReset packet = (PacketReset)obj;
	
			players = packet.getPlayers();
			stateGame = packet.getState();
			updateNicks(players);
			map = packet.getMap();
			scoreBoard = packet.getScoreBoard();
			this.topScore = packet.getTopScore();
			isReset=true;
			for(int i=0; i < 10 ; i++)
			{
				System.out.println(topScore[i].getNick());
			}
		}
		else if (obj.getClass() == PacketTopScore.class)
		{
			PacketTopScore packet = (PacketTopScore) obj;
			this.topScore = packet.getTopScore();
		}
		else if (obj.getClass() == PacketWiner.class)
		{
			PacketWiner packet  = (PacketWiner)obj;
			winerName = packet.getWinerName();
		}
		else if (obj.getClass() == PacketAnimation.class)
		{
			PacketAnimation packet = (PacketAnimation) obj;
			System.out.println(obj.toString());

			handleAnimation(packet);
		
		}
		else if (obj.getClass() == PacketRespawn.class)
		{
			PacketRespawn packet = (PacketRespawn) obj;
			System.out.println(obj.toString());

			respAnim.add(packet.getPosition());
		
		}
		
	}
	private void handleAnimation(PacketAnimation packet)
	{
		exploAnim.add(packet.getPosition());
	}
	public int getID() {
		for (int i = 0; i < players.size(); i++) {

			if (players.get(i).getID() == socket.getLocalPort()) {
				return i;
			}
		}
		return 0;
	}
	public ArrayList<Vector2> getAnimiation()
	{
		ArrayList<Vector2> tmp = new ArrayList<Vector2>();
		for(int i=0; i < exploAnim.size(); i++)
		{
			tmp.add(exploAnim.get(i));
	
	
		}
		exploAnim.clear();
		return tmp ;
	}
	public ArrayList<Vector2> getAnimiationResoawn()
	{
		ArrayList<Vector2> tmp = new ArrayList<Vector2>();
		for(int i=0; i < respAnim.size(); i++)
		{
			tmp.add(respAnim.get(i));
	
	
		}
		respAnim.clear();
		return tmp ;
	}
	public TopScore []  getTopScore()
	{
		return topScore;
	}
	
	public StateMulti getState()
	{
		return stateGame;
	}
	public String getWinerName()
	{
		return winerName;
	}
	private void updateNicks(ArrayList<PlayerData> players)
	{
		playerNicks.clear();
		for(int i=0; i < players.size() ; i++)
		{
			playerNicks.add(new Nicks(players.get(i).getUsername(),players.get(i).getPosition()));
		}
	}
	public ArrayList<Nicks> getNicks()
	{
		return playerNicks;
	}
	public long getTime()
	{
		return timeGame;
	}
	public int getSizeClients()
	{
		return players.size();
	}
	public Message getServerMessage()
	{
		Message tmp = serverMessage;
		serverMessage = null;
		return tmp;
	}
	public ScoreBoard getScoreBoard()
	{
		if(scoreBoard == null)
			return new ScoreBoard();
		else
		return scoreBoard;
	}

	public Vector2 getPlayerPosition(int ID) {
		System.err.println(ID + " " + players.size());
		return players.get(ID).getPosition();
	}

	public ArrayList<PlayerData> getPlayers() {
			return players;

	}

	public PlayerData getPlayer(int ID) {
		return players.get(ID);
	}

	public BulletsHandler getBullets() {
		return bulletHandler;
	}

	public void handleLogin(PacketLoged packet) {

		this.isLogin = packet.getLogin();
		System.out.println(isLogin + " packet ");
		if (isLogin == true) {
			players = packet.getPlayers();
			map = packet.getMap();
		}

	}

	public PacketTile getUpdateTile()
	{
		return map.getUpdateTile();
	}
	public MapFromServer getMap() {
		return map;
	}

	public void sendLogin(String username, String password) {

		PacketLogin login = new PacketLogin(socket.getPort());
		login.setUsername(username);
		login.setPassword(password);
		sendLogin = true;
		send(login);

	}

	public void sendRequestForTopScore()
	{
		send(new PacketRequest(true));
	}
	public void sendShoot(BulletMulti bullet) {

		// System.out.println("Shoot" + "" + bullet.getID() + " " +
		// bullet.getPosition().x + " "+ bullet.getPosition().y)
		send(new PacketShoot(bullet));
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void sendMove(Vector2 pos) {
		// TODO Auto-generated method stub
		send(new PacketMove(socket.getLocalPort(), pos));

	}

	public void sendRetryGame(boolean state)
	{
		send(new PacketRetryGame(state, getPort()));
	}
	public void sendDisconnect(boolean state) {
		send(new PacketDisconnect(true));
		/*
		 * try { streamOut.writeObject(new PacketDisconnect(true)); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	public void sendRegister(String username, String password, String data) {
		sendRegister = true;
		send(new PacketRegister(username, password, data));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void stop() throws IOException {
		if (socket != null)
			socket.close();
		if (streamIn != null)
			streamIn.close();
		if (streamOut != null)
			streamOut.close();
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public boolean isConnected() {
		return socket.isConnected();
	}

	public boolean getLoginPacket() {
		// TODO Auto-generated method stub
		return getLoged;
	}

	public void setLoged(boolean state) {
		getLoged = state;
	}

	public int IDClient() {
		return IDClient;
	}

	public int getPort() {
		// TODO Auto-generated method stub
		return socket.getLocalPort();
	}
}
