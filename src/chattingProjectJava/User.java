package chattingProjectJava;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	
	public int id;
	private String userName;
	private long phoneNumber;
	private String password;
	private List<Contact> contactList;
	private List<Message> userMessages;

	public User(int id, String userName, long phoneNumber, String password) {
		this.id = id;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.contactList = new ArrayList<Contact>();
		this.userMessages = new ArrayList<Message>();
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public List<Message> getUserMessages() {
		return userMessages;
	}

}
