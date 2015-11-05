package ServerGame;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.net.InetAddress;
import java.net.Socket;


public class ServerThread extends Thread {
	private Server server = null;
	private Socket socket = null;
	private int ID = -1;
	private ObjectInputStream streamIn = null;
	private ObjectOutputStream streamOut = null;
	private boolean isLooped = false;

	public ServerThread(Server _server, Socket _socket) {
		super();
		server = _server;
		socket = _socket;
		ID = socket.getPort();
		isLooped = true;
	}

	public synchronized void send(Object obj) {
		try {
		//	System.out.println("wysy³am "+obj.toString()+ " do " + socket.getPort() );
			
			streamOut.writeObject(obj);

			streamOut.reset();
		} catch (IOException ioe) {
			System.out.println(ID + " ERROR sending: " + ioe.getMessage());
			
		//	stop();
		}
	}

	public int getID() {
		return ID;
	}
	public InetAddress getAddress()
	{
		return socket.getInetAddress();
	}
	public void setLoop(boolean state)
	{
		isLooped = state;
	}

	public void run()  {
		System.out.println("Server Thread " + ID + " running.");
		while (isLooped) {
			try {
					
				server.handle(streamIn.readObject(),socket.getPort());
				
				//System.out.println(getAddress());
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isLooped=false;
				server.remove(socket.getPort());
				
				System.out.println("Remove " + socket.getPort());
		
			}

		}
	}
	public int getPort()
	{
		return socket.getPort();
	}
	public static ObjectInputStream createInputStream(Socket socket)
			throws IOException {
		InputStream stream = socket.getInputStream();

		return new ObjectInputStream(stream);

	}

	public static ObjectOutputStream createOutputStream(Socket socket)
			throws IOException {
		OutputStream stream = socket.getOutputStream();

		try {
			return new ObjectOutputStream(stream);
		} catch (IOException ex) {
			stream.close();

			throw ex;
		}
	}

	public void open() throws IOException {
		if (socket.isConnected()){
		streamIn = ServerThread.createInputStream(socket);// new
															// ObjectInputStream(socket.getInputStream());
		streamOut = ServerThread.createOutputStream(socket); // new
		}							// ObjectOutputStream(socket.getOutputStream());

	}

	public void close() throws IOException {
		System.out.println("close");
		if (socket != null)
			socket.close();
		if (streamIn != null)
			streamIn.close();
		if (streamOut != null)
			streamOut.close();
	}

}
