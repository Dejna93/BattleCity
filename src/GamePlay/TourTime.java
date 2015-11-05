package GamePlay;

import com.badlogic.gdx.utils.TimeUtils;

public class TourTime {

	long startTime;
	TimeUtils time;
	long eclapseTime=0;
	public TourTime() {
		// TODO Auto-generated constructor stub
		startTime = TimeUtils.millis();
		time = new TimeUtils();	
	}
	public void update()
	{
		eclapseTime = time.timeSinceMillis(startTime)/1000;
	//s	System.out.println(eclapseTime);
		//time.timeSinceMillis(startTime)/1000
	}
	public void reset()
	{
		eclapseTime = 0 ;
		startTime = TimeUtils.millis();
	}
	public long getTime()
	{
		return eclapseTime;
	}
}
