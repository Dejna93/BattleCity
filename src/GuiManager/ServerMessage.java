package GuiManager;

import Utils.Message;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ServerMessage {

	private Message msg;
	private BitmapFont font;
	private boolean show = false;

	public ServerMessage() {
		// TODO Auto-generated constructor stub
		font = new BitmapFont(Gdx.files.internal("assets/font/font.fnt"),
				Gdx.files.internal("assets/font/font.png"), false);
	}

	public void update(Message msg,int size) {
		
		if (msg != null) {
		//	System.out.println(msg.getMessage() + " " + msg.getShow());
			this.msg = msg;
			show = true;
		} else{
			show = false;
		}
	}

	public void draw(SpriteBatch sb) {

		if(msg !=null)
		{
		if (msg.getShow()) {
			font.setScale(2.0f);
			font.draw(sb, msg.getMessage(), 100, 300);
		}
		}
		
	}
}
