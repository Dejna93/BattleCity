package Utils;

import java.io.Serializable;



public class Score implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7084392456247528245L;
	
	private int ID;
	private int score=0;
	private String nick;
	public Score(int score)
	{
		this.score = score;
	}
	
	public Score(int ID,int score,String nick) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.score = score;
		this.nick = nick;
	}
	public Score(int ID,String nick) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.score = 0;
		this.nick = nick;
	}
	public void updateScore(int score)
	{
		
		this.score +=score;
		System.out.println(this.score);

	}
	public Score getScore()
	{
		return new Score(ID, score,nick);
	}
	public int getIScore()
	{
		return score;
	}

	public int getID()
	{
		return ID;
	}
	public String getNick()
	{
		return nick;
	}
}
