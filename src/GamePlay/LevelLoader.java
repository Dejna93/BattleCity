package GamePlay;

import java.io.IOException;

import XMLPraser.LoadList;

public class LevelLoader {

	private LoadList loadList;
	public LevelLoader() {
		// TODO Auto-generated constructor stub
		try {
			loadList = new LoadList("levels/levels.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
