package states;

import java.io.IOException;

import handlers.DatebaseHandler;
import handlers.GameStateManager;
import network.Connection;
import AssetsLoader.Assets;
import DialogPopUp.DialogBadLogin;
import DialogPopUp.DialogServerDown;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class LoginState extends GameState {

	Skin skin;
	Stage stage;

	private Table table;

	private DialogBadLogin dialogBadLogin;
	private DialogServerDown dialogServer;
	private TextButton loginButton;
	private TextButton registerButton;
	private TextField userField;
	private TextField passwordField;
	private TextField addressField;
	private TextField portField;
	private DatebaseHandler db;
	private Label userLabel;
	private Label passLabel;
	private Label addressLabel;
	private Label portLabel;
	private Connection connection;
	private boolean iscon = false;
	private NinePatch nine;
	public LoginState(GameStateManager gSManager) {
		super(gSManager);

		System.err.println("new client running");
		// TODO Auto-generated constructor stub
		stage = new Stage();

		skin = new Skin(Gdx.files.internal("assets/json/uiskin.json"));
		dialogBadLogin = new DialogBadLogin("Problem with login", skin);
		dialogServer = new DialogServerDown("Server doesnt respond", skin);
		// db = new DatebaseHandler();

		loginButton = new TextButton("Logowanie", skin, "default");
		registerButton = new TextButton("Rejestracja", skin, "default");

		addressField = new TextField("", skin);
		portField = new TextField("7777", skin);
		userField = new TextField("", skin);
		passwordField = new TextField("", skin);

		passwordField.setPasswordMode(true);
		passwordField.setPasswordCharacter('*');

		userLabel = new Label("Username", skin);
	
		passLabel = new Label("Password", skin);
		addressLabel = new Label("Server IP", skin);
		portLabel = new Label("Port Server", skin);
		table = new Table();

		table.setFillParent(true);
		
		nine = new NinePatch(Assets.getManager().get("assets/texture/background.png",Texture.class));
		
		table.setBackground(new NinePatchDrawable(nine));
		// table.debug();
		generate();
		login();
		register();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events

		stage.addActor(table);
		connection = new Connection();
		 //table.setDebug(true);

		/*
		 * try { connection = new Connection(); } catch (Exception e) {
		 * e.printStackTrace();
		 * 
		 * }
		 */

	}

	private void generate() {
		table.center().center();
		table.add(addressLabel);
		table.add(portLabel).left().width(50);
		table.row();
		table.add(addressField).width(200).pad(20);
		table.add(portField).width(80).center();

		table.row();
		table.add(userLabel).pad(5).center();
		table.add(userField).fillX().pad(10);
		table.row();
		table.add(passLabel).pad(5).center();
		table.add(passwordField).fillX().pad(10);
		table.row();
		table.add(registerButton);
		table.add(loginButton);

		stage.addActor(table);
	}

	private void login() {

		loginButton.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				try {
					if(iscon!=true)
					{connection = new Connection(checkAddress(addressField
							.getText()), Integer.parseInt(portField.getText()));
					iscon = true;
					}
					String a = (String) userField.getText().trim();
					String b = (String) passwordField.getText().trim();

					connection.sendLogin(a, b);
				} catch (Exception e) {
					dialogServer.show(stage);


				}

				// if(connection.sendLogin == true)
				// {
				//while (connection.getLoged != true) {
			//		System.out.println("waiting");
	
			//	}
				// }

				if (connection.isLogin() == true) {
					gSManager.pushState(gSManager.MULTIPLAYER, connection);// MULTIPLAYER,connection);
				}

				else if (connection.sendLogin == true
						&& connection.isLogin() == false && connection.isConnected()) {
				
					System.err.println("bad login");
					dialogBadLogin.show(stage);
			
				}

			}
		});
	}

	private void register() {
		registerButton.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				try{
				if(iscon!=true)
					connection = new Connection(checkAddress(addressField
							.getText()), Integer.parseInt(portField.getText()));
				iscon=true;
				System.out.println(connection.isConnected() + " register");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				gSManager.popState();
				gSManager.pushState(gSManager.REGISTER, connection);
			}
		});
	}

	private String checkAddress(String address) {
		if (address.length() == 0) {
			return new String("localhost");
		}
		return address;

	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		if (connection != null) {
			
			if (connection.isLogin() == true) {
				gSManager.pushState(gSManager.MULTIPLAYER, connection);// MULTIPLAYER,connection);
			}
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
