package Packet;

import java.io.Serializable;
import java.util.ArrayList;

import ServerGame.PlayerData;
import ServerGame.Players;
import ServerGame.ScoreBoard;
import Utils.Message;
import Utils.StateMulti;




public class PacketUpdate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7124716839376247415L;


	private ArrayList<PlayerData> data;
	private ArrayList<String> scoreTop= new ArrayList<String>();
	private ScoreBoard scoreBoard = new ScoreBoard();
	private Message message;
	private long time;
	private  StateMulti state;
	public PacketUpdate(ArrayList<PlayerData> data) {    //dla 1 gracza ;/
		// TODO Auto-generated constructor stub

		this.data = new ArrayList<PlayerData>(data);
	}
	public PacketUpdate(Players players,ScoreBoard scoreBoard,Message message,long time, StateMulti state)
	{

		this.data =players.getPlayers();
		this.scoreBoard = scoreBoard;
		this.message = message;
		this.time = time;
		this.state = state;
	}

	public StateMulti getState()
	{
		return state;
	}
	public ScoreBoard getScoreBoard()
	{
		return scoreBoard;
	}
	public Message getMessage()
	{
		return message;
	}
	public ArrayList<PlayerData> getPlayers()
	{
		return this.data;
	}
	public long getTime()
	{
		return time;
	}
}
