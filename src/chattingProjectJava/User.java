package chattingProjectJava;

public class User {

	public int id;
	private String userName;
	private long phoneNumber;
	private String password;

	Util server = Util.getUtilObject();
	ChattingApp main = ChattingApp.getInstance();

	public User() {
	}

	public User(int id, String userName, long phoneNumber, String password) {
		this.id = id;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public User(int id, String contactName, long contactNumber) {
		this.id = id;
		userName = contactName;
		phoneNumber = contactNumber;
	}

//	public int getId() {
//		return id;
//	}

	public String getUserName() {
		return userName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

//	private String getPassword() {
//		return password;
//	}

}
