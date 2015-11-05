package GuiManager;

import ServerGame.ScoreBoard;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GuiMultiScore {
	private BitmapFont font = new BitmapFont();

	private Vector2 position = new Vector2();
	private ScoreBoard scoreBoard= new ScoreBoard();
	
	private SpriteBatch sb;

	public GuiMultiScore(float x, float y, BitmapFont font) {

		// TODO Auto-generated constructor stub
		this.position.x = x;
		this.position.y = y;
		this.font = font;

	}

	public GuiMultiScore(Vector2 position, BitmapFont font) {
		// TODO Auto-generated constructor stub

		this.position = position;
		this.font = font;
	}

	public void update(ScoreBoard scoreBoard) {
	
		this.scoreBoard = scoreBoard;
	}

	public void draw(SpriteBatch sb) {
		this.sb = sb;
	//	font.draw(sb, title, position.x, position.y);
			for(int i=0; i < scoreBoard.size() ; i++)
			{
				//System.out.println(scoreBoard.getScore(i).getNick() + " " + scoreBoard.getScore(i).getIScore());
				font.draw(sb, scoreBoard.getScore(i).getNick() + " " + scoreBoard.getScore(i).getIScore(), position.x  , position.y -i *32);
			}
	}
}
