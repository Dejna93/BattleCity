package GuiManager;

import AssetsLoader.Assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GuiEnemySpawn {
private BitmapFont font = new BitmapFont();
	
	private Vector2 position = new Vector2();
	
	private Texture enemyT = Assets.getManager().get("assets/texture/enemy.png",Texture.class);
	private String title= "Enemy on screen: ";
	private int enemySize = 5;
	private SpriteBatch sb;
	public GuiEnemySpawn(float x, float y,BitmapFont font) {

		// TODO Auto-generated constructor stub
		this.position.x = x ;
		this.position.y = y ;
		this.font =font ;
		title += Integer.toString(enemySize);

	}
	public GuiEnemySpawn(Vector2 position ,BitmapFont font) {
		// TODO Auto-generated constructor stub


		this.position = position;
		this.font =font  ;
	}
	public void update()
	{
		
	}
	public void draw(SpriteBatch sb)
	{
	this.sb =sb;
	
		font.draw(sb, title, position.x, position.y);
		for(int i=0; i < enemySize ; i++)
		{
			sb.draw(enemyT, position.x+i*32, position.y-92);
		}
	//	font.draw(sb, score, position.x + (title.length())*8, position.y);
	}
}