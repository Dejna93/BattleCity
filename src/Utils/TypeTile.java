package Utils;

import java.io.Serializable;

public class TypeTile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7462097610654164549L;
	public enum typeTile
	{
		GROUND,BRICK,GRASS,STONE,WATER;

		public Utils.TypeTile.typeTile getType(String type) {
			// TODO Auto-generated method stub
			if(typeTile.GROUND.name().equals(type))
			{
				return typeTile.GROUND;
			}
			if(typeTile.BRICK.name().equals(type))
			{
				return typeTile.BRICK;
			}
			if(typeTile.GRASS.name().equals(type))
			{
				return typeTile.GRASS;
			}
			if(typeTile.STONE.name().equals(type))
			{
				return typeTile.STONE;
			}
			if(typeTile.WATER.name().equals(type))
			{
				return typeTile.WATER;
			}
			return null;
		}
	};
	public static typeTile getType(String type)
	{
		if(typeTile.GROUND.name().equals(type))
		{
			return typeTile.GROUND;
		}
		return null;
	}
}
