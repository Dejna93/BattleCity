package XMLPraser;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class LoaderLevels {

	private ArrayList<String> levelMaps = new ArrayList<String>();
	
	public LoaderLevels(String filePath) throws IOException {
		// TODO Auto-generated constructor stub
	
		try{
			XmlReader reader = new XmlReader();

			Element root = reader.parse(new FileHandle(filePath));
			System.out.println(root.getChildCount());
			for(int i=0 ; i < root.getChildCount();i++)
			{
				levelMaps.add(root.getChild(i).getAttribute("filepath"));
			}

		}catch(Exception e)
		{
		e.printStackTrace();
		}
	
	}
	public ArrayList<String> getLevels()
	{
		return levelMaps;
	}
}
