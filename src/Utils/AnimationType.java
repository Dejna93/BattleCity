package Utils;

import com.badlogic.gdx.math.Vector2;

public class AnimationType  {

	int type;
	Vector2 position;
	
	public AnimationType(Vector2 position,int id) {
		// TODO Auto-generated constructor stub
		this.position = position;
		this.type = id;
	}
	public Vector2 getPosition()
	{
		return position;
	}
	public int getType()
	{
		return type;
	}
}
