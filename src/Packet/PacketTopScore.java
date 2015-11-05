package Packet;

import java.io.Serializable;

import Utils.TopScore;

public class PacketTopScore implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6646383123729765440L;
	
	private TopScore [] topScore;
	
	
	public PacketTopScore ( TopScore [] topScore) {
		// TODO Auto-generated constructor stub
		this.topScore = topScore;
	}
	public TopScore [] getTopScore()
	{
		return topScore;
	}

}
