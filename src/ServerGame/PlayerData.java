package ServerGame;

import java.io.Serializable;

import Utils.Direction;
import Utils.Score;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4168716596929732412L;
	private Vector2 position;
	private String username;
	private int ID = -1;
	private Direction.direction direction;
	private Rectangle bounds = new Rectangle();

	private Score score;

	private boolean retryGame = true;
	public PlayerData(int ID, String username, Vector2 pos) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.username = username;
		this.position = pos;
	
		score = new Score(ID,username);
		bounds.set(position.x + 16, position.y + 16, 32, 32);
		setDirection(Utils.Direction.direction.Up);
		//System.err.println(position.x + " " + position.y );
	}

	public PlayerData(int ID, PlayerData data) {
		// TODO Auto-generated constructor stub
		this.position = data.getPosition();
		this.username = data.getUsername();
		this.ID = ID;
		this.direction = data.direction;
		score = new Score(ID,username);
	}

	public void respawn(Vector2 respawnPoint)
	{
		System.out.println(position.x + " " + position.y + " respawn point " +respawnPoint.x + " " + respawnPoint.y );
		setPosition(respawnPoint);
		bounds.setPosition(respawnPoint);
	}
	public Score getScore()
	{
		return score;
	}
	public String getUsername() {
		return username;
	}

	public int getID() {
		return ID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 pos) {
		this.position = pos;
	}

	public void move(Vector2 move) {

		if (position.x + move.x < 0) {
			move.x = 0;
		} else if (position.x + move.x > 610) {
			move.x = 0;
		}
		if (position.y + move.y < 0) {
			move.y = 0;
		} else if (position.y + move.y > 450) {
			move.y = 0;
		}

		this.position.x += move.x;
		this.position.y += move.y;

		bounds.setPosition(position);

		setDirection(move);
		System.err.println(position.x + " " + position.y );
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setDirection(Vector2 move) {
		if (move.x < 0)
			setDirection(direction.Left);
		else if (move.x > 0)
			setDirection(direction.Right);
		else if (move.y > 0)
			setDirection(direction.Up);
		else if (move.y < 0)
			setDirection(direction.Down);
	}

	public void setRetry(boolean state)
	{
		this.retryGame = state;
	}
	public boolean getRetry()
	{
		return retryGame;
	}
	public Utils.Direction.direction getDirection() {
		return direction;
	}

	public void setDirection(Direction.direction direction) {
		this.direction = direction;
	}
}
