package Animation;

import java.util.ArrayList;

import AssetsLoader.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Respawn extends AnimeType {

	
	private boolean isInit = false;
	private Vector2 position;
	public Respawn( ) {
		
		// TODO Auto-generated constructor stub
		texturAtlas = Assets.getManager().get("assets/animation/respawn/respawn.atlas",TextureAtlas.class);
		animation = new Animation(0.5f, texturAtlas.getRegions());
		
	}




	@Override
	public void draw(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
		if (isInit == true) {
		
			eclapsedTime += Gdx.graphics.getDeltaTime();
			sb.draw(animation.getKeyFrame(eclapsedTime,true), position.x+8,
					position.y+8);
			
		}
		if (animation.isAnimationFinished(eclapsedTime)) {
			isInit = false;
			eclapsedTime = 0;
		}

	}

	@Override
	protected void dipose() {
		// TODO Auto-generated method stub
		sb.dispose();
		texturAtlas.dispose();
	}

	@Override
	public void update(boolean isInitial, Vector2 position, float dt) {
		// TODO Auto-generated method stub


	}

	public void update(ArrayList<Vector2> pos) {
		//System.out.println(pos.size());
		
		for(int i=0;i<pos.size();i++)
		{
				isInit = true;
				position = pos.get(i);
		}	
	}




	@Override
	protected void update(float dt) {
		// TODO Auto-generated method stub
		
	}


}
