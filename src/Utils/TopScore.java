package Utils;

import java.io.Serializable;

public class TopScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7880307984580419484L;
	String nr = "Nr ";
	String nick = "";
	String score = "";

	public TopScore(String nr) {
		// TODO Auto-generated constructor stub
		this.nr += nr;
	}
	

	public void setNick(String nick) {
		this.nick = nick;
	}


	public void setScore(String score) {
		this.score = score;
	}


	public String getNumber() {
		return nr;
	}

	public String getNick() {
		return nick;
	}

	public String getScore() {
		return score;
	}
}
