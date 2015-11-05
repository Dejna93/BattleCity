package GamePlay;

import ServerGame.Players;



public class ControllGame {

	private TourTime tourTime;
	
	private Players players;
	private long tourLong;
	private boolean reset= false;
	private long resetingTime =0;
	private boolean firstStart= true;
	public ControllGame(int tourLong) {
		// TODO Auto-generated constructor stub

		this.tourLong = tourLong;
		this.tourTime = new TourTime();
		
	}
	public void setTourTime(long time)
	{
		System.out.println("Set time " + time);
		this.tourLong = time;
	}
	public boolean update(Players players)
	{
		
		this.players = players;


	//	System.out.println(tourTime.getTime());
		if(players.size() >1 && tourTime.getTime() < tourLong)
		{
		//	System.out.println("Clock counting");
			tourTime.update();
			return true;
		}
		else if (tourTime.getTime() >= tourLong  && tourTime.getTime() < tourLong +10)
		{
			//System.out.println("Clock restarting");
			tourTime.update();
			resetingTime = tourTime.getTime() - 10;
			reset = true;
			return false;
		}
		else if (tourTime.getTime() == tourLong +10)
		{
			reset = false;
			tourTime.reset();
		}
		/*else if (playersConnected <1 )
		{		
			tourTime.reset();
			return false;
		}*/
		tourTime.reset();
		return false;
	}

	public boolean playersWannaPlay()
	{
		if(players.size()>1)
			return true;
		return false;
		/*for(int i=0; i < players.size() ; i++)
		{
			if(players.getPlayer(i).getRetry() == false)
				return false;
		}
		return true;*/
	}
	public String toClockFormat(long time)
	{
		long s = time % 60;
		return String.format(" %02d",s);
	}
	public String getServerTime()
	{
		return toClockFormat(tourTime.getTime());
	}
	public long getTime()
	{
		return tourTime.getTime();
	}
	public boolean getReset()
	{
		return reset;
	}
}
