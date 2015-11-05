package Animation;

import java.util.ArrayList;

import Utils.AnimationType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Explosion extends AnimeType {

	private boolean isInit = false;
	private Vector2 position;

	public Explosion() {
		// TODO Auto-generated constructor stub
		
		texturAtlas = new TextureAtlas(
				Gdx.files.internal("assets/animation/explosion.atlas"));
		animation = new Animation(1 / 5f, texturAtlas.getRegions());
	
	}

	@Override
	public void draw(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
		if (isInit == true) {
		
			eclapsedTime += Gdx.graphics.getDeltaTime();
			sb.draw(animation.getKeyFrame(eclapsedTime,true), position.x,
					position.y);
			
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
