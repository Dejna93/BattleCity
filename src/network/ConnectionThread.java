package network;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import java.net.Socket;


public class ConnectionThread extends Thread {

	private Socket socket = null;
	private Connection client = null;
	private ObjectInputStream streamIn = null;
	private boolean isLogin = false;
	public ConnectionThread(Connection connection, Socket socket) {
		// TODO Auto-generated constructor stub
		client = connection;
		this.socket = socket;
		openStream();
		start();
	}
	private void openStream() {
		// TODO Auto-generated method stub
		try {
			streamIn  = ConnectionThread.createInputStream(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static ObjectInputStream createInputStream( Socket socket ) throws IOException
	{
		InputStream stream = socket.getInputStream();
		//BufferedInputStream buffer = null;
		
	//	try
	//	{
	//		buffer = new BufferedInputStream( stream );
		//	
			return new ObjectInputStream( stream );
	//	}
	//	catch ( IOException ex )
	//	{
	//		if ( buffer != null )
	//			Utils.close( buffer );
			
	//		stream.close();
	//		
	//		throw ex;
	//	}
	}

	public void run()
	{
	
		while(true)
			{
			
			//	System.out.println(socket.isInputShutdown());
				try {
				
					client.handle(streamIn.readObject());
					
					//if(streamIn.read() == -1) break;
					
				} catch (ClassNotFoundException | IOException  e) {
					// TODO Auto-generated catch block
					close();
				//	System.out.println("thread");
					//e.printStackTrace();
				
				}
			}	
	
	}
	public void close()
	{
		try{
			if(streamIn != null) streamIn.close();
		}catch(IOException e)
		{
			try {
				client.stop();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	

}
