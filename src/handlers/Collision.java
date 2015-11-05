package handlers;

import java.util.ArrayList;
import java.util.Vector;

import org.lwjgl.Sys;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import Tiles.Tile;
import Utils.TankUtil;

public class Collision {

	private Tile[][] tMap = new Tile[20][15];

	private Rectangle bounds;
	private Rectangle player;
	private ArrayList<TankUtil> tanksRect;

	public Collision(Tile[][] tMap, ArrayList<TankUtil> tanks, Vector2 player) { // for
																					// tank
																					// move
																					// collision
		// TODO Auto-generated constructor stub
		this.tMap = tMap;
		this.tanksRect = tanks;
		this.player = new Rectangle(player.x, player.y, 32, 32);
	}

	public boolean possibleMove(float x, float y) {
		bounds = new Rectangle(x, y, 32, 32);

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 15; j++) {
				if (bounds.overlaps(tMap[i][j].getBounds())
						&& (tMap[i][j].getOptions().equals("block") || tMap[i][j]
								.getOptions().equals("shotable"))) {
					return false;
				}

			}
		}
		if (player.overlaps(bounds)) {
			return false;
		}
		for (int i = 0; i < tanksRect.size(); i++) {
			if (bounds.overlaps(tanksRect.get(i).getBounds())) {
				return false;
			}

		}
		return true;

	}

}
