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

	private User() {
	}

	private User(int id, String userName, long phoneNumber, String password) {
		this.id = id;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.contactList = new ArrayList<Contact>();
		this.userMessages = new ArrayList<Message>();
	}

	public User(String userName, long phoneNumber) {
		this.userName = userName;
		this.phoneNumber = phoneNumber;
	}

	public boolean isExistContact(long contactNumber) {
		if (contactList == null || contactList.size() == 0) {
			return false;
		} else {
			for (int i = 0; i < contactList.size(); i++) {
				if (contactNumber == contactList.get(i).getPhoneNumber()) {
					return true;
				}
			}
		}
		return false;
	}

	private int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	private String getPassword() {
		return password;
	}

	private List<Contact> getContactList() {
		return contactList;
	}

	private List<Message> getUserMessages() {
		return userMessages;
	}

}
