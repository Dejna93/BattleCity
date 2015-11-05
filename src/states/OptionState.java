package states;

import java.util.ArrayList;

import network.Connection;
import handlers.DatebaseHandler;
import handlers.GameStateManager;
import DialogPopUp.DialogExit;
import DialogPopUp.DialogTopScore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class OptionState extends GameState {

	private Skin skin;
	private Stage stage;

	private Table table;
	private Table cont;
	private TextField chatInput;
	private TextButton chatSend;

	private List list;
	private DatebaseHandler db;
	private ScrollPane scrollPane;
	private 	DialogExit dialogExit;
	private DialogTopScore dialogTop;
	private Connection conn = new Connection();
	ArrayList<String> msg = new ArrayList<String>();

	public OptionState(GameStateManager gSManager) {
		super(gSManager);
		db = new DatebaseHandler();
		// TODO Auto-generated constructor stub
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("assets/json/uiskin.json"));

		
		//dialogTop = new DialogTopScore("TOP SCORE", skin, db,conn,gSManager);
		Gdx.input.setInputProcessor(stage);// Make the stage consume events
		dialogExit = new DialogExit("Exit", skin);
		dialogTop.show(stage);
		
		/*
		 * cont = new Table(); list = new List(skin);
		 * 
		 * scrollPane = new ScrollPane(cont,skin);
		 * 
		 * 
		 * chatInput = new TextField("", skin); chatSend = new
		 * TextButton("Send", skin);
		 * 
		 * table.setFillParent(true); cont.align(Align.left);
		 * 
		 * table.add(scrollPane).fill(); Gdx.input.setInputProcessor(stage);//
		 * Make the stage consume events
		 * 
		 * chatWindow();
		 */

		//stage.addActor(table);

		//table.debug();

	//	scoreTop();
	}

	public void scoreTop() {
		Label[][] label = new Label[10][2];
		String[] score = new String[10];
		
		Window window = new Dialog("Server doesnt respond", skin);
	//	window.setSize(500, 500);
		//score = db.getTopScore();
	
		try {
			for (int i = 0; i < 10; i++) {
				if(score[i].length()>4)
				{
					label[i][0] = new Label(score[i].substring(0,4) + " ", skin);
				
				label[i][1] = new Label(
						score[i].substring(4, score[i].length()), skin);
				}	else
					label[i][1] = new Label("Empty", skin);
				table.add(label[i][0]);
				table.add(label[i][1]);
				table.row();

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	
		Button accept = new TextButton("Accept", skin);

		window.setPosition(Gdx.graphics.getWidth() / 2 - 150,
				Gdx.graphics.getHeight() / 2 - 50);

		
		window.debug();
		 window.setMovable(false);
		
		//window.setTitle("Error communication");
		window.add(table).fill();
		//window.setSize(300,400);
		window.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				stage.clear();
				
			}
		});
		stage.addActor(window);
		

	}

	public void chatWindow() {

		chatSend.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				Label label = new Label(validate(chatInput.getText()), skin);
				label.setWrap(false);
				System.err.println(label.getText());
				cont.add(label);
				// msg.add(validate(chatInput.getText()));
			}
		});

		table.row();
		table.add(chatInput);
		table.add(chatSend);
		table.row();

	}

	private String validate(String msg) {
		int size = msg.length();
		StringBuffer buffor = new StringBuffer();
		for (int i = 0; i < msg.length(); i++) {
			if (i % 20 == 0 && i != 0) {
				buffor = new StringBuffer(msg);
				buffor.insert(i, "\n");
				msg = buffor.toString();
			}
		}

		return msg;
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			dialogExit.show(stage);
		/*
		 * if (msg.size() > 5) { msg.remove(0);
		 * 
		 * } list.setItems(msg.toArray());
		 */
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		update(Gdx.graphics.getDeltaTime());
		stage.act();
		stage.draw();

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		// sb.begin();
		// sb.enableBlending();

		// sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
