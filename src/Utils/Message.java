package Utils;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996916619145523392L;

	private String message;
	
	private boolean show= true;
	public Message(String message,boolean show) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.show = show;
	
	}
	public Message(boolean show) {
		// TODO Auto-generated constructor stub
		this.show = show;
	
	}
	public String getMessage()
	{
		return message;
	}
	public boolean getShow()
	{
		return show;
	}
}
