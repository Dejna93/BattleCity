package XMLPraser;




public class Tile {

	private String posx;
	private String posy;
	private String type;
	


	public Tile(String posx,String posy,String type) {
		this.posx = posx;
		this.posy = posy;
		this.type = type;
		
	}
	public String getPosx()
	{
		return posx;
	}
	public String getPosy()
	{
		return posy;
	}
	public String getType()
	{
		return type;
	}
}
