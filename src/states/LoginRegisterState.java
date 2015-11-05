package states;



import java.text.SimpleDateFormat;
import java.util.Date;

import network.Connection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import handlers.DatebaseHandler;
import handlers.GameStateManager;

public class LoginRegisterState extends GameState {

	Skin skin;
	Stage stage;

	private Table table;
	
	private TextButton registerButton;

	private TextField userField;
	private TextField passwordField;
	private TextField passwreapetField;
	
	private TextButton back;
	private Label userLabel;
	private Label passLabel;
	private Label pass2Label;
	
	private Connection connection;
	
	public LoginRegisterState(GameStateManager gSManager) {
		super(gSManager);
		// TODO Auto-generated constructor stub

	}
	public LoginRegisterState(GameStateManager gSManager, Connection conn) {
		super(gSManager);
		
		stage = new Stage();
		this.connection = new Connection();
		connection = conn;

		
		
		skin = new Skin(Gdx.files.internal("assets/json/uiskin.json"));
		back = new TextButton("Back", skin);
		
		registerButton = new TextButton("Rejestracja",skin,"default");
		userField  = new TextField("", skin);
		passwordField = new TextField("", skin);
		passwreapetField = new TextField("", skin);
		passwordField.setPasswordMode(true);
		passwordField.setPasswordCharacter('*');
		passwreapetField.setPasswordMode(true);
		passwreapetField.setPasswordCharacter('*');

		table = new Table();
		table.setFillParent(true);
		
		userLabel= new Label("Username", skin);
		passLabel = new Label("Password", skin);
		pass2Label = new Label("Repeat", skin);
		
		
        Gdx.input.setInputProcessor(stage);// Make the stage consume events
      
   
        stage.addActor(table);
       
		registerPanel();
	}

	private void registerPanel()
	{
	
		table.add(userLabel).width(100);
		table.add(userField);
		table.row();
		table.add(passLabel);
		table.add(passwordField);
		table.row();
		table.add(pass2Label);
		table.add(passwreapetField);
		table.row();	
		table.add(back);
		table.add(registerButton);
	
		stage.addActor(table);
		register();
		
		back.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				gSManager.popState();
				gSManager.pushState(gSManager.LOGIN);
			}
		});
	}
	
	private void register()
	{
		registerButton.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				if(!getUsername().equals("") && getPassword().equals(getRepeat()))
				{
					Date dt = new Date();
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println("user + pass");
					connection.sendRegister(getUsername(), getPassword(), sdf.format(dt));
				}
				System.out.println(connection.isConnected() +  " click" );
			}
		});
	}
	
	private String getUsername()
	{
		return userField.getText();
	}
	private String getPassword()
	{
		return passwordField.getText();
	}
	private String getRepeat()
	{
		return passwreapetField.getText();
	}
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
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
