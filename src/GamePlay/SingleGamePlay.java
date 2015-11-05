package GamePlay;

import handlers.EnemySpawner;
import entities.Player;

public class SingleGamePlay {

	private Player player;
	private EnemySpawner enemies;
	
	public SingleGamePlay(Player player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	public void update(EnemySpawner enemies)
	{
		this.enemies = enemies;
		shotManager();
		
	}
	private void shotManager()
	{
		for(int i=0; i < player.bulletsize(); i++)
		{
			for (int j=0; j < enemies.getTanks().size() ; j++ )
			{
				if(player.getBoundsBullet(i).overlaps(enemies.getTanks().get(j)))
				{
					enemies.remove(j);
					player.removeBullet(i);
					break;
				}
			}
		}
	}
}
