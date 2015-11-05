package states;

import handlers.GameStateManager;
import AssetsLoader.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class MenuState extends GameState implements Screen {
	


	private Skin skin;
	private Stage stage;
	
	private Table table;
	
	private TextButton newGameButton;
	private TextButton multiButton;
	private TextButton optionButton;
	private TextButton exitButton;
	 
	private Texture text;
	public MenuState(GameStateManager gSManager) {
		super(gSManager);
		// TODO Auto-generated constructor stub
			
	
		 	stage = new Stage();
			skin = new Skin(Gdx.files.internal("assets/json/uiskin.json"));
			
	        Gdx.input.setInputProcessor(stage);// Make the stage consume events
	 
	        table = new Table();
	        table.setFillParent(true);
	      
	        text = Assets.getManager().get("assets/texture/menu.png",Texture.class);
	  
	        menuView();
	        
	        stage.addActor(table);
	       // createBasicSkin();
	
			handleEvent();
	}

	private void menuView()
	{
		newGameButton = new TextButton("Test", skin); // Use the initialized skin
        multiButton = new TextButton("Multiplayer", skin); // Use the initialized skin
    //    optionButton = new TextButton("Option", skin);
        exitButton = new TextButton("Exit", skin);
        
        table.align(Align.center);
      //  table.add(newGameButton);
       // table.row();
 
        table.add(multiButton).minWidth(200).minHeight(50).pad(10, 10, 10, 10);
        table.row();
          table.add(newGameButton).minWidth(200).minHeight(50).pad(10, 10, 10, 10);
         table.row();
       // table.add(optionButton);
       // table.row();
        table.add(exitButton).minWidth(200).minHeight(50).pad(10, 10, 10, 10);
	}
	
	private void handleEvent()
	{
	newGameButton.addListener(new ChangeListener() {
	    	   public void changed (ChangeEvent event, Actor actor) {
	    		 //System.out.println("Clicked! Is checked: " + button.isChecked());
	    		 gSManager.pushState(GameStateManager.PLAY);
	    		 stage.clear();
	    		}
	       });
	       multiButton.addListener(new ChangeListener() {
	    	   public void changed (ChangeEvent event, Actor actor) {
	    		 stage.clear();
	    		 gSManager.popState();
	    		 gSManager.pushState(GameStateManager.LOGIN);
	    		
	    		}
	       });
	       /*
	       optionButton.addListener(new ChangeListener() {
	    	   public void changed (ChangeEvent event, Actor actor) {
	    		 stage.clear();
	    		 gSManager.popState();
	    		 gSManager.pushState(GameStateManager.OPTION);
	    		 
	    		 
	    		}
	       });*/
	       exitButton.addListener(new ChangeListener() {
	    	   public void changed (ChangeEvent event, Actor actor) {
	    		   Gdx.app.exit();
	    		}
	       });
	}
	
	@Override
	public void resize (int width, int height) {
	}
	 
	@Override
	public void dispose () {
	stage.dispose();
	skin.dispose();
	}
	 
	@Override
	public void show() {
	// TODO Auto-generated method stub
	 
	}
	 
	@Override
	public void hide() {
	// TODO Auto-generated method stub
	 
	}
	 
	@Override
	public void pause() {
	// TODO Auto-generated method stub
	 
	}
	 
	@Override
	public void resume() {
	// TODO Auto-generated method stub
	 
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
        gSManager.game().getSpriteBatch().begin();
        gSManager.game().getSpriteBatch().draw(text,75,0);
        gSManager.game().getSpriteBatch().end();
        stage.act();
        stage.draw();
	}



	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		
	}
	}