package handlers;

import com.badlogic.gdx.math.Rectangle;

import Tiles.Tile;

public class C {

	private float x, y;
	private int Index, Jndex;

	private Tile[] nodes;
	private Tile[][] tMap = new Tile[20][15];

	private Rectangle bounds;
	public C(float x, float y, Tile[][] tMap) {
		// TODO Auto-generated constructor stub
		this.tMap = tMap;
		nodes = new Tile[4];

		bounds = new Rectangle(x+16, y+16, 32, 32);
		setIndex(bounds);
		System.out.println(" x " + x + " y " + y + " - index [i][j] " + Index
				+ " " + Jndex +" " + tMap[Index][Jndex].getBounds().x + " " + tMap[Index][Jndex].getBounds().y);
		splitTile();

	}

	public void setIndex(Rectangle bounds)
	{
		for(int i=0; i < 20 ; i++)
		{
			for(int j=0; j < 15;j++)
			{
				if(bounds.overlaps(tMap[i][j].getBounds()))
				{
					
					Index = i;
					Jndex = j;
				}
			}
		}
	}
	private void splitTile() {
		if (Index > 0)
			nodes[0] = tMap[Index-1][Jndex]; // lewo
		if (Index < 19)
			nodes[1] = tMap[Index + 1][Jndex]; // prawo
		if (Jndex < 14)
			nodes[2] = tMap[Index][Jndex + 1]; // gora
		if (Jndex > 0)
			nodes[3] = tMap[Index][Jndex - 1]; // dol
		
		//for(int i =0 ; i < 4; i++)
		//{
	//		System.out.println("Node " + i + " " + nodes[i].getBounds().x + " " + nodes[i].getBounds().y);
	//	}
	}
	public boolean isCollideLeft()
	{
		if( nodes[0] !=null)
		{
			//System.out.println((x+16) + " " + (y+16));
			System.out.println("Node " + (Index-1) + " " + Jndex+ " " + nodes[0].getBounds().x + " " + nodes[0].getBounds().y);
			return true;
		}
		return false;
	}

}
