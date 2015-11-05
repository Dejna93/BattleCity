package states;

import handlers.GameStateManager;
import AssetsLoader.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SplashScreen extends GameState{
	

	Sprite background;
	BitmapFont font;
	Texture emptyT;
	Texture fullT;
	NinePatch empty;
	NinePatch full;
	
	private static final AssetManager manager = new AssetManager();
	
	public SplashScreen(GameStateManager gSManager) {
		super(gSManager);
		// TODO Auto-generated constructor stub
		Assets.loader();
		background = new Sprite(new Texture("load/background.png"));
		background.setSize(800, 480);;
		font=new BitmapFont();
		emptyT=new Texture(Gdx.files.internal("load/empty.png"));
		fullT=new Texture(Gdx.files.internal("load/full.png"));
		empty=new NinePatch(new TextureRegion(emptyT,24,24),8,8,8,8);
		full=new NinePatch(new TextureRegion(fullT,24,24),8,8,8,8);
		
		/*manager.load("assets/texture/player_up.png", Texture.class);
		manager.load("assets/texture/enemy_tank_1.png", Texture.class);
		manager.load( "levels/tileset.pack",TextureAtlas.class);
		manager.load("assets/texturepack/animation.pack", TextureAtlas.class);
		manager.load("assets/test.wav",Sound.class);
		manager.load("assets/test1.wav",Sound.class);
		manager.load("assets/test2.wav",Sound.class);
		manager.load("assets/test3.wav",Sound.class);
		manager.load("assets/test4.wav",Sound.class);
	*/
		
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
		cam.update();
		
		sb.begin();
		background.draw(sb);
		empty.draw(sb,120,100,580,30);
		full.draw(sb, 120,100,Assets.getManager().getProgress()*580,30);
		font.drawMultiLine(sb,(int)(Assets.getManager().getProgress()*100)+"% loaded " +  Assets.getManager().getQueuedAssets()+" file loaded",310,150,0, BitmapFont.HAlignment.CENTER);
		
		sb.end();
		if(Assets.getManager().update() )
		{
		//	System.out.println(Assets.getManager().getProgress()*100 + "% loaded");
			gSManager.popState();
			gSManager.pushState(gSManager.MENU);
		}

	
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
