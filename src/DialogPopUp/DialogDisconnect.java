package DialogPopUp;



import java.io.IOException;

import handlers.GameStateManager;
import network.Connection;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DialogDisconnect extends Dialog {

	private Connection conn;
	private GameStateManager gSManager;
	public DialogDisconnect(String title, Skin skin,Connection conn,GameStateManager gSManager) {
		super(title, skin);
		// TODO Auto-generated constructor stub
		{
			this.gSManager = gSManager;
			this.conn = conn;
			text("\n");
			button("Yes",true);
			button("No",false);
		}
	}

	@Override
	public Dialog button(Button button, Object object) {
		// TODO Auto-generated method stub
		return super.button(button, object);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		super.cancel();
	}

	@Override
	public Table getButtonTable() {
		// TODO Auto-generated method stub
		return super.getButtonTable();
	}

	@Override
	public Table getContentTable() {
		// TODO Auto-generated method stub
		return super.getContentTable();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	protected void result(Object object) {
		// TODO Auto-generated method stub
		super.result(object);
		boolean state = (boolean) object;
		if(state == true)
		{
			System.out.println("Disconnect");
			conn.sendDisconnect(true);
			try {
				conn.stop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gSManager.popState();
			gSManager.pushState(gSManager.MENU);
			
			hide();
		}
		else
			hide();
	}

	@Override
	public Dialog show(Stage stage, Action action) {
		// TODO Auto-generated method stub
		return super.show(stage, action);
	}

	@Override
	public Dialog show(Stage stage) {
		// TODO Auto-generated method stub
		return super.show(stage);
	}

	@Override
	public Dialog text(Label label) {
		// TODO Auto-generated method stub
		return super.text(label);
	}

	@Override
	public Dialog text(String text, LabelStyle labelStyle) {
		// TODO Auto-generated method stub
		return super.text(text, labelStyle);
	}

	@Override
	public Dialog text(String text) {
		// TODO Auto-generated method stub
		return super.text(text);
	}
	

}
