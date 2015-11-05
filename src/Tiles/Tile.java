package Tiles;

import java.io.Serializable;
import java.util.HashMap;

import AssetsLoader.Assets;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3311660912732546111L;

	private Rectangle bounds;

	private TextureAtlas atlasTile;
	private Sprite sprite;

	private int width = 32;
	private int height = 32;

	private Vector2 origin;
	private HashMap<Integer, String> option;
	private int type;
	private Vector2 position;
	public Tile()
	{}
	public Tile(int x, int y, int type, HashMap<Integer, String> option,
			boolean isSingle) {
		// TODO Auto-generated constructor stub
		this.type = type;

		if (isSingle) {
			updateType();
			sprite.setSize(32, 32);
			sprite.setPosition(x, y);
		}
		this.option = option;
		origin = new Vector2(x,y);
		bounds = new Rectangle(x, y, 32, 32);
		position = new Vector2(x, y);
	}

	public Tile(Rectangle bounds, int type, String option) {
		//System.out.println(type);
		this.type = type;
		// this.option = new HashMap<Integer, String>());
		this.bounds = bounds;

		updateType();

		sprite.setSize(width, height);
		sprite.setPosition(bounds.x, bounds.y);
	}

	public Tile(Tile tile) {
		this.type = tile.getType();
		// this.option.put(type, tile.getOptions());
		updateType();

		sprite.setSize(32, 32);
		sprite.setPosition(tile.getBounds().x, tile.getBounds().y);

		bounds = new Rectangle(tile.getBounds());

	}

	public Vector2 getOrigin()
	{
		return origin;
	}
	public void getTileServer() {

	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String getOptions() {
		return option.get(type);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void updateType(int type)
	{
		this.type = type;
		updateType();
	}
	public void updateType() {
		if (type == 1)
			sprite = new Sprite(Assets.getManager().get("assets/tile/none.png",Texture.class));
			//sprite = new Sprite(Assets.getManager().get("assets/tile/none.png",Texture.class));
		else if (type == 2)
			sprite = new Sprite(Assets.getManager().get("assets/tile/brick.png", Texture.class));
			//sprite = new Sprite(Assets.getManager().get("assets/texture/debug.png",Texture.class));
		else if (type == 3)
			// textRegion = atlasTile.findRegion("stone");
			sprite = new Sprite(Assets.getManager().get("assets/tile/stone.png", Texture.class));
			//sprite = new Sprite(Assets.getManager().get("assets/texture/debug1.png",Texture.class));
		else if (type == 4)
			
			// textRegion = atlasTile.findRegion("grass");
			sprite = new Sprite(Assets.getManager().get("assets/tile/grass.png", Texture.class));
			//sprite = new Sprite(Assets.getManager().get("assets/texture/debug1.png",Texture.class));
		else if (type == 5)
			sprite = new Sprite(Assets.getManager().get("assets/tile/water.png", Texture.class));
			//sprite = new Sprite(Assets.getManager().get("assets/texture/debug1.png",Texture.class));
		else if(type ==6)
			sprite = new Sprite(Assets.getManager().get("assets/tile/herb.png", Texture.class));
	}

	public void remove() {
		sprite = new Sprite(Assets.getManager().get("assets/tile/none.png",
				Texture.class));
		type = 1;
	}

	public Vector2 getPosition()
	{
		return position;
	}
	public void draw(SpriteBatch sb) {
		sprite.draw(sb);
	}

}
