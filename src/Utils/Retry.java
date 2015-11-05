package Utils;

public class Retry {

	private int port;
	private boolean state;
	
	public Retry(int port, boolean state) {
		// TODO Auto-generated constructor stub
		this.port = port;
		this.state = state;
	}
	public int getPort()
	{
		return port;
	}
	public boolean getRetry()
	{
		return state;
	}
}
