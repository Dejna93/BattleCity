package GuiManager;

import javax.xml.ws.handler.MessageContext.Scope;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



public class GuiScore{

	private BitmapFont font = new BitmapFont();
	
	private Vector2 position = new Vector2();
	private String title = "Score: ";
	private String score = "0";
	private SpriteBatch sb;
	public GuiScore(float x, float y,BitmapFont font) {

		// TODO Auto-generated constructor stub
		this.position.x = x ;
		this.position.y = y ;
		this.font =font ;
	
	}
	public GuiScore(Vector2 position ,BitmapFont font) {
		// TODO Auto-generated constructor stub


		this.position = position;
		this.font =font  ;
	}
	public void update(String score)
	{
		this.score = score;
	}
	public void draw(SpriteBatch sb)
	{
	this.sb =sb;
		font.draw(sb, title, position.x, position.y);
		font.draw(sb, score, position.x + (title.length())*8, position.y);
	}
}
