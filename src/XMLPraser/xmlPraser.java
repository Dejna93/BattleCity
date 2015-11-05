package XMLPraser;
import java.io.File;
import java.util.ArrayList;

import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;



public class xmlPraser {

	//No generics
	ArrayList<Tile> myData = new ArrayList<Tile>();
	Document dom;

	public xmlPraser() {
		//create a list to hold the data

		//initialize the list
		loadData();

		//Get a DOM object
		createDocument();
	}


	public void runExample(){
		System.out.println("Started .. ");
		createDOMTree();
		printToFile();
		System.out.println("Generated file successfully.");
	}

	/**
	 * Add a list of books to the list
	 * In a production system you might populate the list from a DB
	 */
	private void loadData(){
		for (int x =0 ; x < 640 ; x +=32)
		{
			for (int y =0 ; y < 480 ; y +=32)
			{
				myData.add(new Tile(toString(x),toString(y),toString(random())));
			
			}
		}
		
	}
	private String toString(int x)
	{
		return Integer.toString(x);
	}
	private int random()
	{
		Random rand = new Random();
		return rand.nextInt(4);
	}
	/**
	 * Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory
	 */
	private void createDocument() {

		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		//get an instance of builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//create an instance of DOM
		dom = db.newDocument();

		}catch(ParserConfigurationException pce) {
			//dump it
			System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
			System.exit(1);
		}

	}

	/**
	 * The real workhorse which creates the XML structure
	 */
	private void createDOMTree(){

		//create the root element <Books>
		Element rootEle = dom.createElement("Tilemap");
		dom.appendChild(rootEle);

		//No enhanced for

		for (int i=0 ; i < myData.size();i++) {

			//For each Book object  create <Book> element and attach it to root
			Element bookEle = createBookElement(myData.get(i));
			rootEle.appendChild(bookEle);
		}

	}


	private Element createBookElement(Tile tile){

		Element bookEle = dom.createElement("Tile");


		Element posxEle = dom.createElement("Posx");
		Text posxText = dom.createTextNode(tile.getPosx());
		posxEle.appendChild(posxText);
		bookEle.appendChild(posxEle);


		Element posyEle = dom.createElement("Posy");
		Text posyText = dom.createTextNode(tile.getPosy());
		posyEle.appendChild(posyText);
		bookEle.appendChild(posyEle);
		
		Element typeEle = dom.createElement("Type");
		Text typeText = dom.createTextNode(tile.getType());
		typeEle.appendChild(typeText);
		bookEle.appendChild(typeEle);
	

		return bookEle;

	}

	/**
	 * This method uses Xerces specific classes
	 * prints the XML document to file.
     */
	private void printToFile(){
		try{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(dom);
		StreamResult result = new StreamResult(new File("levels/level1.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
		 } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
		
		
	}


	public static void main(String args[]) {
		long start=System.currentTimeMillis();
		//create an instance
		xmlPraser xce = new xmlPraser();

		//run the example
		xce.runExample();
		long stop=System.currentTimeMillis();
		System.out.println("Czas wykonania (w milisekundach): "+(stop-start));
	}
}