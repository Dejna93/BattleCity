package Animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public abstract class AnimeType {
	protected SpriteBatch sb;
	protected TextureAtlas texturAtlas;
	protected Animation animation;
	protected float eclapsedTime;
	
	protected abstract void update(boolean isInitia,Vector2 position,float dt);
	protected abstract void update(float dt);
	protected abstract void draw(SpriteBatch sb);
	protected abstract void dipose();
	

	
}
