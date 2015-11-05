package Chat;

import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Clipboard;

public class ChatTextField extends Widget  {
	
	private TextFieldStyle style;
	private Skin skin;
	private String text, messagText;
	int cursor;
	private Clipboard clipboard;
	
	private Rectangle fieldBouds = new Rectangle();
	private TextBounds textBounds = new TextBounds();
	
	public ChatTextField(String text, Skin skin) {
		// TODO Auto-generated constructor stub
		this.skin = skin;
		
	}
	
	
}
