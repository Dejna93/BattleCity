package SqlPackage;

public class SqlUsers {

	private int ID;
	private String Users;
	private String Password;
	private String Date;
	
	public SqlUsers(int ID,String Users,String Password,String Date) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.Users = Users;
		this.Password = Password;
		this.Date = Date;
	}

	public int getID() {
		return ID;
	}

	public String getUsers() {
		return Users;
	}

	public String getPassword() {
		return Password;
	}

	public String getDate() {
		return Date;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public void setUsers(String users) {
		this.Users = users;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public void setDate(String date) {
		this.Date = date;
	}
	
	
}
