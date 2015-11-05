package handlers;



import java.util.Stack;




import network.Connection;
import main.Game;
import states.GameState;
import states.LoginRegisterState;
import states.LoginState;
import states.MenuState;
import states.MultiplayerState;
import states.OptionState;
import states.PlaySingle;
import states.SplashScreen;

public class GameStateManager {

	private Game game;

	private Stack<GameState> gameStates; //stos do roznych poziomow 
	
	//zmienne do stanow gry
	public static final int LOADING = 21351;
	public static final int MENU = 53132;
	public static final int PLAY = 41232;
	public static final int LOGIN = 12442;
	public static final int REGISTER = 35325;
	public static final int OPTION =99113;
	public static final int MULTIPLAYER =85662;

	public static final int SERVER = 5412312;
	public GameStateManager(Game game)
	{
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(LOADING);
	}

	
	public void update(float dt)
	{
		gameStates.peek().update(dt);
	}
	public void render()
	{
		gameStates.peek().render();
	}
	public Game game() {return game;}
	
	private GameState getState(int state)
	{
		if (state == LOADING)
			return new SplashScreen(this);
		if (state == MENU)
			return new MenuState(this);
		if (state == PLAY)
			return new PlaySingle(this);
		if (state == LOGIN)
			return new LoginState(this);
		if (state == REGISTER)
			return new LoginRegisterState(this);
		if(state == OPTION)
			return new OptionState(this);
		if (state ==MULTIPLAYER)
			return new MultiplayerState(this);

		return null;
	}
	private GameState getState(int state,Connection conn)
	{
		if(state == MULTIPLAYER)
			return new MultiplayerState(this, conn);
		if (state ==REGISTER)
			return new LoginRegisterState(this,conn);
		return null;
	}
	
	public void setState (int state)
	{
		popState();
		pushState(state);
	}
	public void pushState(int state)
	{
		gameStates.push(getState(state));
	}
	public void pushState(int state,Connection conn)
	{
		gameStates.push(getState(state, conn));
	}
	public void popState()
	{
		GameState gam = gameStates.pop();
		gam.dispose();
	}
}
