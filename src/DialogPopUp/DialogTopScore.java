package DialogPopUp;

import java.util.ArrayList;

import network.Connection;
import handlers.DatebaseHandler;
import handlers.GameStateManager;
import Utils.TopScore;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class DialogTopScore extends Dialog {

	private GameStateManager gsm;
	private Connection conn;

	private Skin skin;
	private TopScore[] score = new TopScore[10];

	public DialogTopScore(String title, Skin skin, Connection conn,
			GameStateManager gsm) {
		super(title, skin);
		// TODO Auto-generated constructor stub
		this.skin = skin;
		this.gsm = gsm;
		this.conn = conn;
		setModal(true);
		setMovable(true);

		button("Resume", true);
		
	
		conn.sendRequestForTopScore();

	}


	private String toNumer(String score) {
		if (score.length() < 5)
			return "";
		if (score.substring(0, 3).equals("Nr "))
			return score.substring(0, 5);

		return "";
	}

	private String getNick(String score) {
		String result = "";
		score = score.substring(5, score.length());
		if (score.length() > 5) {
			for (int i = 0; i < score.length(); i++) {
				String c = String.valueOf(score.charAt(i));
				if (!c.equals(" ")) {
					result += c;
				} else
					return result;
			}

		}
		return "Empty";

	}

	private String getScore(String score) {
		String tmp = score.substring(5, score.length());
		String result = "";
		for (int i = 0; i < tmp.length(); i++) {
			String c = String.valueOf(tmp.charAt(i));
			if (c.matches("[0-9]")) {
				result += c;
			}
		}
		if (result.length() > 0)
			return result;
		else
			return "0";
	}

	@Override
	public Dialog button(Button button, Object object) {
		// TODO Auto-generated method stub
		return super.button(button, object);
	}

	@Override
	public Dialog button(String text, Object object) {
		// TODO Auto-generated method stub
		return super.button(text, object);
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

		if (state) {
			hide();
		}
	}

	@Override
	public Dialog show(Stage stage, Action action) {
		// TODO Auto-generated method stub
		System.out.println("asdasdasda");

		return super.show(stage, action);

	}

	public void setScore(TopScore[] arrayList) {
		if (arrayList != null) {
			for (int i = 0; i < 10; i++) {

				score[i] = arrayList[i];
			}
		}
	}

	@Override
	public Dialog show(Stage stage) {
		// TODO Auto-generated method stub
		
		System.out.println("Show");
		
		score = conn.getTopScore();
		// window.setSize(500, 500);
		// score = db.getTopScore();
		// getContentTable().debug();
		getContentTable().clear();
		
		LabelStyle style = skin.get(LabelStyle.class);
		style.font.setScale(0.7f);
		 try {
			for (int i = 0; i < 10; i++) {
		
			
				getContentTable().add(new Label(score[i].getNumber(), style))
						.left();
				getContentTable().add(new Label(score[i].getNick(), style))
						.left().fillX();
				getContentTable().add(new Label(score[i].getScore(), skin))
						.right().fillX();
				getContentTable().row();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.show(stage);

	}

	@Override
	public Dialog text(String text) {
		// TODO Auto-generated method stub
		return super.text(text);
	}

}
