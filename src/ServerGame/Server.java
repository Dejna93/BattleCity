package ServerGame;

import handlers.DatebaseHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import GamePlay.MultiGamePlay;
import Packet.*;

public class Server implements Runnable {

	private ArrayList<ServerThread> clients = new ArrayList<ServerThread>();

	private ServerSocket server = null;
	private Thread thread = null;
	public int clientCount = 0;

	private int port = 7777;

	private MultiGamePlay multiGameplay;

	public Server() {

		multiGameplay = new MultiGamePlay(this);
	}

	public Server(int port) {

		multiGameplay = new MultiGamePlay(this);
		runServer();

	}

	public void runServer() {
		try {
			System.out
					.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			start();

		} catch (IOException ioe) {
			System.out.println("Can not bind to port " + port + ": "
					+ ioe.getMessage());
		}
	}

	public void run() {

		while (thread != null) {
			try {
				System.out.println("Waiting for a client ...");
				addThread(server.accept());
			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
			}
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	public ArrayList<ServerThread> getClients() {
		return clients;
	}

	public void handle(Object obj, int port) {

		if (obj.getClass() == PacketMove.class) {
			System.out.println("packetmove" + getID(port));
			multiGameplay.handleMove(obj, port);
		}
		if (obj.getClass() == PacketLogin.class) {
			System.out.println("packetlogin " + getID(port) + " " + port);
			PacketLogin log = (PacketLogin) obj;
			multiGameplay.handleLogin(log, port);
		}
		if (obj.getClass() == PacketDisconnect.class) {
		}
		if (obj.getClass() == PacketRegister.class) {
			PacketRegister packet = (PacketRegister) obj;
			multiGameplay.handleRegister(packet, port);
		}
		if (obj.getClass() == PacketShoot.class) {
			PacketShoot packet = (PacketShoot) obj;
			multiGameplay.handleShoot(packet);
		}
		if(obj.getClass() == PacketDisconnect.class)
		{
			PacketDisconnect packet = (PacketDisconnect)obj;
			multiGameplay.handleDisconnect(packet, port);
		}
		if(obj.getClass() == PacketRetryGame.class)
		{
			PacketRetryGame packet = (PacketRetryGame) obj;
			multiGameplay.handleRetry(packet);
		}
		if(obj.getClass() == PacketRequest.class)
		{
			System.err.println(obj.toString());
			PacketRequest packet = (PacketRequest) obj;
			multiGameplay.handleRequestDB(packet,port);
		}

	}
	public void setTime(long time)
	{
		multiGameplay.setTourTime(time);
	}
	
	public String getCurrentNameLevel()
	{
		return multiGameplay.getCurrentNameLevel();
	}
	
	public int playersSize()
	{
		return multiGameplay.getPlayers().size();
	}
	public String time()
	{
		return multiGameplay.getTime();
	}
	public String getPort()
	{
		
			try {
				return server.getInetAddress().getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";

	}
	public int getID(int port) {
		for (int i = 0; i < clientCount; i++)
			if (clients.get(i).getPort() == port)
				return i;
		System.err.println("Port nie znaleziony kod " + "-1");
		return -1;
	}
	public int getPort(int id)
	{
		return clients.get(id).getPort();
	}

	public synchronized void broadcast(Object object) {

		for (int i = 0; i < multiGameplay.getPlayers().size(); i++) {
			// System.out.println(object.toString());
			clients.get(i).send(object);
		}
	}
	public void send(Object object,int port)
	{
		clients.get(getID(port)).send(object);
	}

	
	public synchronized void remove(int ID) {

		 try{
		int id = getID(ID);
		multiGameplay.getPlayers().remove(id);
		multiGameplay.getScoreBoard().remove(id);
	
		clients.get(id).close();
		clients.remove(id);
	

		broadcast(new PacketUpdate(multiGameplay.getPlayers().getPlayers()));
		clientCount = multiGameplay.getPlayers().size();
		System.out.println("Usuniety klient" + ID + " " + " "
				+ multiGameplay.getPlayers().size());
		 }catch(Exception ioe)
		 {
		 ioe.printStackTrace();
		 }

	}

	public void update() {
		multiGameplay.update();
	}

	private void addThread(Socket socket) {
		// if (clientCount < 2) {
		System.out.println("Client accepted: " + socket + clients.size());
		if(clients.size() <4)
		{
		clients.add(new ServerThread(this, socket));

		try {
			clients.get(clientCount).open();
			System.err.println("open");
			clients.get(clientCount).start();
			System.err.println("start");
			clientCount++;

		} catch (IOException ioe) {
			System.out.println("Error opening thread: " + ioe + " "
					+ clientCount);
		}
		}else {
			 System.out.println("Client refused: maximum " + " reached.");
		}
		// } else
		// System.out.println("Client refused: maximum " + " reached.");
	}

}
