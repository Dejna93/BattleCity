package GuiManager;

import AssetsLoader.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GuiWiner {

private BitmapFont font = new BitmapFont();
	
	private Vector2 position = new Vector2();
	private String nickWiner = "";
	private Texture image;
	private SpriteBatch sb;
	public GuiWiner(float x, float y) {
		// TODO Auto-generated constructor stub
		
		font = new BitmapFont(Gdx.files.internal("assets/font/font.fnt"),
				Gdx.files.internal("assets/font/font.png"), false);

		this.position.x = x ;
		this.position.y = y ;
		
		image = Assets.getManager().get("assets/texture/winerpanel.png",Texture.class);
	
	}

	public void update(String winer)
	{
		this.nickWiner = winer;
	}

	public void draw(SpriteBatch sb)
	{
	this.sb =sb;
		sb.draw(image,position.x , position.y);
		font.draw(sb,"WINNER IS ?",position.x+80,position.y+330);
		font.draw(sb, nickWiner, position.x+150, position.y+200);
		//font.draw(sb, , position.x, position.y);
		
	}
}
