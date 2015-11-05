package GuiManager;

import handlers.GameStateManager;
import network.Connection;
import AssetsLoader.Assets;
import DialogPopUp.DialogDisconnect;
import DialogPopUp.DialogTopScore;
import ServerGame.ScoreBoard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class Gui {

	protected int WIDTH = 160;
	protected int HEIGHT = 480;

	private GuiScore guiScore;
	private GuiEnemySpawn guiEnemy;
	private GuiMultiScore guiMultiScore;
	private GuiClock guiClock;
	protected BitmapFont font;
	protected SpriteBatch sb;

	private TextButton but;
	private TextButton button;
	private DialogTopScore dialogTopScore;
	private DialogDisconnect dialogDisconnect;
	private boolean isMulti = false;
	private Skin skin;
	private Stage stage;
	private Texture panel;
	public Gui(boolean isMulti,Skin skin,Stage stage,Connection conn,GameStateManager gsm) {
		this.skin = skin;
		this.stage = stage;
		// TODO Auto-generated constructor stub
		font = new BitmapFont(Gdx.files.internal("assets/font/font.fnt"),
				Gdx.files.internal("assets/font/font.png"), false);
		font.setScale(0.4f);
		button =  new TextButton("Top Score", skin, "default");
		button.setPosition(666, 24);
		but = new TextButton("X",skin,"default");
		but.setPosition(758, 24);
		panel = Assets.getManager().get("assets/texture/panel.png",
				Texture.class);
		dialogDisconnect = new DialogDisconnect("Disconnect?", skin, conn, gsm);
		if (isMulti) {
			guiClock = new GuiClock(709, 308, font);
			guiMultiScore = new GuiMultiScore(692, 444, font);
			stage.addActor(button);
			stage.addActor(but);
			
			
		} else {
			guiScore = new GuiScore(650, 470, font);
			guiEnemy = new GuiEnemySpawn(650, 370, font);
		}
		this.isMulti = isMulti;

		dialogTopScore = new DialogTopScore("TOP SCORE", skin,conn,gsm);
	}
	public Gui(boolean isMulti) {
		// TODO Auto-generated constructor stub
		font = new BitmapFont(Gdx.files.internal("assets/font/font.fnt"),
				Gdx.files.internal("assets/font/font.png"), false);
		font.setScale(0.4f);

		if (isMulti) {
			guiClock = new GuiClock(650, 470, font);
			guiMultiScore = new GuiMultiScore(650, 370, font);
		
		
		} else {
			guiScore = new GuiScore(650, 470, font);
			guiEnemy = new GuiEnemySpawn(650, 370, font);
		}
		this.isMulti = isMulti;

	}

	public void update(ScoreBoard scoreBoard,long time) {
		if(isMulti)
		{	guiMultiScore.update(scoreBoard);
			guiClock.update(time);
		}
		 button.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				dialogTopScore.show(stage);
				
				dialogTopScore.setPosition(670, 60);
			}
		});
		 but.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent arg0, Actor arg1) {
				// TODO Auto-generated method stub
				dialogDisconnect.show(stage);
			}
		});
	}

	public BitmapFont getFont() {
		return font;
	}

	public void draw(SpriteBatch sb) {

		if(isMulti)
		{	sb.draw(panel,640,0);
			guiMultiScore.draw(sb);
			guiClock.draw(sb);
			
		}
		else{
			guiScore.draw(sb);
			guiEnemy.draw(sb);
		}
	}

}
