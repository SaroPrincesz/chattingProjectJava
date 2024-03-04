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

// create Contact
	public void createNewContact(User user) {
		System.out.print("Enter the Contact Name: ");
		String contactName = server.getString();

		System.out.print("Enter the Contact Number: ");
		long contactNumber = server.getLong();

		if (!isExistContact(user.id, contactNumber)) {
			User contactUser = main.isExistPhoneNumber(contactNumber);
			if (contactUser != null) {
				server.getContactList().add(new Contact(user.id, contactName, contactNumber, contactUser.id));
				System.out.println("Contact Added...!!!");
				main.subMenu(user);
			} else {
				System.out.println("This contact isn't use Chatting App");
				main.subMenu(user);
			}
		} else {
			System.out.println("This contact already  exist");
			main.subMenu(user);
		}
	}

// is Exist Contact (In User's contact list)
	public boolean isExistContact(int id, long contactNumber) {
		if (server.getContactList() == null || server.getContactList().size() == 0) {
			return false;
		} else {
			for (int i = 0; i < server.getContactList().size(); i++) {
				if (server.getContactList().get(i).id == id
						&& server.getContactList().get(i).getPhoneNumber() == contactNumber) {
					return true;
				}
			}
		}
		return false;
	}

// view Contact
	public void viewContact(User user) {
		if (server.getContactList() == null || server.getContactList().size() == 0) {
			System.out.println("No Contact...!!");
		} else {
			for (int i = 0; i < server.getContactList().size(); i++) {
				if (server.getContactList().get(i).id == user.id) {
					System.out.print("Contact Name: " + server.getContactList().get(i).getUserName());
					System.out.println("   Contact Name: " + server.getContactList().get(i).getPhoneNumber());
				}
			}
		}
		main.subMenu(user);
	}

//Get User Name
	public String getUserName() {
		return userName;
	}

//Get Phone Number
	public long getPhoneNumber() {
		return phoneNumber;
	}

}
