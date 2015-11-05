package Packet;

import java.io.Serializable;
import java.util.ArrayList;

import ServerGame.MapGame;
import ServerGame.PlayerData;
import ServerGame.Players;
import ServerGame.ScoreBoard;
import Tiles.Tile;
import Utils.MapFromServer;
import Utils.StateMulti;
import Utils.TopScore;

public class PacketReset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8007147916007280569L;
	private MapFromServer map;
	private ArrayList<PlayerData> players;
	private ScoreBoard scoreBoard;
	private TopScore[] topScore;
	private StateMulti state;

	public PacketReset(MapFromServer map, Players players, ScoreBoard scoreBoard,
			TopScore[] topScore, StateMulti state) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.topScore = topScore;
		this.players = players.getPlayers();
		this.scoreBoard = scoreBoard;
		this.state = state;
	}
	public StateMulti getState()
	{
		return state;
	}
	public MapFromServer getMap() {
		return map;
	}

	public ArrayList<PlayerData> getPlayers() {
		return players;
	}

	public ScoreBoard getScoreBoard() {
		return scoreBoard;
	}

	public TopScore[] getTopScore() {
		return topScore;
	}

}
