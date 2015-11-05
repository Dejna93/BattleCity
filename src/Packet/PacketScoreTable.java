package Packet;

import java.io.Serializable;

import ServerGame.ScoreBoard;

public class PacketScoreTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7047516357618659119L;
	
	private ScoreBoard scoreBoard = new ScoreBoard();
	public PacketScoreTable(ScoreBoard scoreBoard) {
		// TODO Auto-generated constructor stub
		this.scoreBoard = scoreBoard;
	}
	public ScoreBoard getScoreBoard()
	{
		return scoreBoard; 
			
	}

}
