package Animation;

import java.util.ArrayList;

import AssetsLoader.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Water  extends AnimeType{

	private ArrayList<Vector2> waterTilePosition = new ArrayList<Vector2>();
	
	public Water(ArrayList<Vector2> waterPosition) {
		// TODO Auto-generated constructor stub
	//	texturAtlas = new TextureAtlas();
		this.waterTilePosition = waterPosition;
		try{
		texturAtlas = Assets.getManager().get("assets/animation/water/water.atlas",TextureAtlas.class);
		animation = new Animation(3f, texturAtlas.getRegions());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void update( float dt) {
		// TODO Auto-generated method stub
		
	}

	public void update(ArrayList<Vector2> waterPosition)
	{
		if(waterPosition.size()==0)
		{
			this.waterTilePosition=waterPosition;
		}
	}
	@Override
	public void draw(SpriteBatch sb) {
		// TODO Auto-generated method stub
		if(waterTilePosition!=null)
		{
			try{
		for(int i=0; i < waterTilePosition.size(); i++)
		{
			eclapsedTime +=Gdx.graphics.getDeltaTime();
			sb.draw(animation.getKeyFrame(eclapsedTime,true),waterTilePosition.get(i).x,waterTilePosition.get(i).y);
		}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void dipose() {
		// TODO Auto-generated method stub
		sb.dispose();
		texturAtlas.dispose();
	}

	@Override
	protected void update(boolean isInitia, Vector2 position, float dt) {
		// TODO Auto-generated method stub
		
	}
	

}
