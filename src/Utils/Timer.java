package Utils;


import com.badlogic.gdx.utils.TimeUtils;

public class Timer {

	long sTime;
	long eclapseT=0;
	long delay;
	TimeUtils time;
	
	public Timer(long delay) {
		// TODO Auto-generated constructor stub
		this.delay = delay;
		time = new TimeUtils();
		sTime = TimeUtils.millis();
		
	}
	public boolean finish()
	{
		eclapseT = time.timeSinceMillis(sTime)/1000;
		System.out.println(eclapseT);
		if(eclapseT >= delay)
			{
			eclapseT = 0 ;
			sTime = TimeUtils.millis();
			return true;
			}
		else
			return false;
	}
}
