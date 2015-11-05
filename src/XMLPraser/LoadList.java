package XMLPraser;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class LoadList {

	private String filepath;
	private ArrayList<String> levels = new ArrayList<String>();
	public LoadList(String filepath) throws IOException {
		// TODO Auto-generated constructor stub
		this.filepath = filepath;
		
		XmlReader reader = new XmlReader();
		Element root = reader.parse(new FileHandle(filepath));
		Element level = root.getChildByName("levels");
		for(int i=0;i < level.getChildCount() ; i++)
		{
			levels.add(level.getAttribute("filepath"));
		}
	}
	public ArrayList<String> getLevel()
	{
		return levels;
	}
}
