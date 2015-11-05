package Utils;

import java.io.Serializable;

public class StateMulti implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3853352084903503544L;
	public enum State{
		WAITING,PLAYING,RESETING
	}
	
	State state;
	public StateMulti() {
		// TODO Auto-generated constructor stub
		state = state.WAITING;
	}
	public State getState()
	{
		return state;
	}
	public void setState(State state)
	{
		this.state = state;
	}
	
}
