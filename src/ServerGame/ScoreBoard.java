package ServerGame;

import handlers.DatebaseHandler;

import java.io.Serializable;
import java.util.ArrayList;

import Utils.Score;
import Utils.ScoreType;

public class ScoreBoard implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5002161047130725921L;
	private ArrayList<Score> scorePlayers;

	public ScoreBoard() {
		// TODO Auto-generated constructor stub
		scorePlayers = new ArrayList<Score>();
	}

	public void addPlayer(PlayerData player) {
		boolean isContain = false;
		
		for(int i=0 ;i< scorePlayers.size() ; i++)
		{
			if(scorePlayers.get(i).getID() == player.getID())
				isContain = true;				
		}
		if (isContain == false)
			scorePlayers.add(new Score(player.getID(),player.getUsername()));
	}

	public void updateScore(Score score)
	{
		for(int i=0 ; i < scorePlayers.size() ; i++)
		{
			if(scorePlayers.get(i).getID() == score.getID())
			{
				scorePlayers.set(i, score);
				System.out.println("update score " );
				

			}
		}
	}

	public void sendScoreToDB(DatebaseHandler db, ScoreBoard scoreBoard)
	{
		this.scorePlayers =scoreBoard.getScoreList();
		for(int i=0 ; i < scoreBoard.getScoreList().size() ;i++)
		{
			System.err.println(scoreBoard.getScoreList().get(i).getIScore() + " " + scoreBoard.getScoreList().get(i).getNick());
			db.sendNewScore(scoreBoard.getScoreList().get(i));
		}
	}
	public int size()
	{
		return scorePlayers.size();
	}
	public Score getScore(int id)
	{
		return scorePlayers.get(id);
	}
	public ArrayList<Score> getScoreList()
	{
		return scorePlayers;
	}

	public void remove(int id)
	{
		scorePlayers.remove(id);
	}
	public void setScore() {
		// TODO Auto-generated method stub
		
	}

	public void reset() {
		// TODO Auto-generated method stub
		for(int i=0; i < scorePlayers.size() ; i++)
		{
			scorePlayers.set(i,new Score(scorePlayers.get(i).getID(), 0, scorePlayers.get(i).getNick()));
		}
	}
	


}
