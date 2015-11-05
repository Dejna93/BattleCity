package DialogPopUp;

import java.io.IOException;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class DialogBadLogin extends Dialog {

	public DialogBadLogin(String title, Skin skin) {
		super(title, skin);
		// TODO Auto-generated constructor stub
		text("Account didnt exist or wrong username \n or password");
		button("Ok");
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
