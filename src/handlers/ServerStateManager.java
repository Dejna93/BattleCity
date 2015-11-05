package handlers;

import java.util.Stack;

import states.ServerState;
import states.ServerWindowState;
import ServerGame.ServerApp;

public class ServerStateManager {

	private ServerApp serverApp;
	
	
	private Stack<ServerState> serverState;
	
	public static final int LOADER = 213123;
	
	public ServerStateManager(ServerApp serverApp)
	{
		this.serverApp = serverApp;
		serverState = new Stack<ServerState>();
		pushState(LOADER);
	}

	public void update(float dt)
	{
		serverState.peek().update(dt);
	}
	public void render()
	{
		serverState.peek().render();
	}
	
	public ServerApp getServerApp()
	{
		return serverApp;
	}
	private ServerState getState(int state)
	{
		if(state == LOADER)
			return new ServerWindowState(this);
		return null;
	}
	public void setState(int state)
	{
		popState();
		pushState(state);
	}
	
	
	private void pushState(int state) {
		// TODO Auto-generated method stub
		serverState.push(getState(state));
	}
	public void popState()
	{
		ServerState state = serverState.pop();
		state.dispose();
	}
}
