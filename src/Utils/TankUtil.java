package Utils;



import com.badlogic.gdx.math.Rectangle;


public class TankUtil{

	private Rectangle bounds;
	private Utils.Direction.direction direction;
	
	public TankUtil(Rectangle bounds,Direction.direction direction) {
		// TODO Auto-generated constructor stub
		this.bounds = bounds;
		this.direction = direction;
	}
	public Rectangle getBounds()
	{
		return bounds;
	}
	public Utils.Direction.direction getDirection()
	{
		return direction;
	}
}
