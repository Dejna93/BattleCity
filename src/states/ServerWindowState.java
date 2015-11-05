package states;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import handlers.ServerStateManager;
import ServerGame.Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class ServerWindowState extends ServerState{

	private Server server = new Server();
	private Stage stage;
	private Skin skin;	
	
	private Table table;
	private TextField portField;
	private TextButton accept;
	private TextButton submit;
	private TextField tourField;
	private Label tourLabel;
	private Label portLabel;
	private BitmapFont font;
	private Label tButton;
	private Label lPlayers;
	private Label lTime;
	private Label levelName;
	public ServerWindowState(ServerStateManager sStateManager) {
		super(sStateManager);
		
		skin = new Skin(Gdx.files.internal("assets/json/uiskin.json"));
		stage = new Stage();
		font = new BitmapFont(Gdx.files.internal("assets/font/font.fnt"),
				Gdx.files.internal("assets/font/font.png"), false);
		table = new Table();

			tButton = new Label("", skin);
			
		lPlayers = new Label("",skin);
		portField = new TextField("", skin);
		accept = new TextButton("Accept", skin);
		lTime = new Label("", skin);
		portLabel = new Label("Server port ", skin);
		
		submit = new TextButton("Accept", skin);
		tourField = new TextField("", skin);
		tourLabel = new Label("Set round", skin);
		table.setFillParent(true);
		levelName = new Label("Level name: ", skin);
		// TODO Auto-generated constructor stub
		Gdx.input.setInputProcessor(stage);
		
		initMenu();
		handleInput();
	    stage.addActor(table);
	    
	}

	public void initMenu()
	{
		stage.clear();
		table.add(portLabel);
		table.add(portField);
		table.row();
		table.add();
		table.add(accept);
	}
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		accept.addListener(new ChangeListener() {
			
	    	   public void changed (ChangeEvent event, Actor actor) {
	    		server = new Server(7777);
	    		tButton.setText(server.getPort());
	    		table.clear();
	    		mainview();
	    		
	    		
	    		}
	       });
	}

	private void mainview()
	{
		table.add(new Label("SERVER IP",skin)).left().pad(10);
		table.add(tButton).center().center();
		table.row();
		table.add(new Label("Players connected", skin)).pad(10);
		table.add(lPlayers);
		table.row();
		table.add(new Label("Round Time", skin)).pad(10);
		table.add(lTime);
		table.row();
		table.add(levelName);
		table.row();
		table.add(tourLabel);
		table.add(tourField);
		table.row();
		table.add(submit).right();
		
		submit.addListener(new ChangeListener() {
			
	    	   public void changed (ChangeEvent event, Actor actor) {
	    		server.setTime(Long.parseLong(tourField.getText()));
	    		tourField.setText("");
	    		}
	       });
		
		
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
	//	System.out.println("jestem");
		if(server!=null)
		{
			server.update();
			lPlayers.setText(String.valueOf(server.playersSize()));
			lTime.setText(server.time());
			levelName.setText("Level name: "+server.getCurrentNameLevel());
		}
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
