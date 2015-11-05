package GuiManager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GuiClock {
	private BitmapFont font = new BitmapFont();
	
	private Vector2 position = new Vector2();
	private String clock = "00:00";
	private long time=0;
	private SpriteBatch sb;
	public GuiClock(float x, float y,BitmapFont font) {

		// TODO Auto-generated constructor stub
		this.position.x = x ;
		this.position.y = y ;
		this.font =font ;
	
	}
	public GuiClock(Vector2 position ,BitmapFont font) {
		// TODO Auto-generated constructor stub


		this.position = position;
		this.font =font  ;
	}
	public void update(long time)
	{
		this.time = time;
	}
	private String toClockFormat(long time)
	{
		long s = time % 60;
		long m =  (time / 60) % 60;
		long h = (time/ (60*60)) % 60;
		return String.format("%02d:%02d:%02d", h,m,s);
	}
	public void draw(SpriteBatch sb)
	{
	this.sb =sb;
		font.draw(sb, toClockFormat(time), position.x, position.y);
		
	}
}
