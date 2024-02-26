package chattingProjectJava;

public class Contact extends User {

	Util server = Util.getUtilObject();
	ChattingApp main = ChattingApp.getInstance();
	private static Contact contact = new Contact();

	public static Contact getContactInstance() {
		return contact;
	}

//Default Constructor
	public Contact() {
	}

//Create new Contact Object
	public Contact(int id, String contactName, long contactNumber) {
		super(id, contactName, contactNumber);
	}

//create Contact
	public void createNewContact(User user) {
		System.out.print("Enter the Contact Name: ");
		String contactName = server.getString();

		System.out.print("Enter the Contact Number: ");
		long contactNumber = server.getLong();

		if (!isExistContact(user.getId(), contactNumber)) {
			if (main.isExistPhoneNumber(contactNumber) != null) {
				server.getContactList().add(new Contact(user.getId(), contactName, contactNumber));
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

//is Exist Contact (In User's contact list) 
	public boolean isExistContact(int id, long contactNumber) {
		if (server.getContactList() == null || server.getContactList().size() == 0) {
			return false;
		} else {
			for (int i = 0; i < server.getContactList().size(); i++) {
				if (server.getContactList().get(i).getId() == id
						&& server.getContactList().get(i).getPhoneNumber() == contactNumber) {
					return true;
				}
			}
		}
		return false;
	}

//view Contact 
	public void viewContact(User user) {
		if (server.getContactList() == null || server.getContactList().size() == 0) {
			System.out.println("No Contact...!!");
		} else {
			for (int i = 0; i < server.getContactList().size(); i++) {
				if (server.getContactList().get(i).getId() == user.getId()) {
					System.out.print("Contact Name: " + server.getContactList().get(i).getUserName());
					System.out.println("   Contact Name: " + server.getContactList().get(i).getPhoneNumber());
				}
			}
		}
		main.subMenu(user);
	}

}
