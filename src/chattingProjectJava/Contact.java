package chattingProjectJava;

public class Contact extends User {
	
	Util server = Util.getObject();
	ChattingApp main = ChattingApp.getInstance();
	
	public Contact(String userName, long phoneNumber) {
		super(userName, phoneNumber);
		
	}

	public void createNewContact(User user) {
		System.out.print("Enter the Contact Name: ");
		String contactName = server.getString();

		System.out.print("Enter the Phone Number: ");
		long contactNumber = server.getLong();

		if (!user.isExistContact(contactNumber)) {
			if(main.isExistPhoneNumber(contactNumber) != null) {
				Contact contact = new Contact(contactName, contactNumber);				
			}else {
				System.out.println("This Contact isn't Use Chatting App...!!");
				main.subMenu(user);
			}
		} else {
			System.out.println("This PhoneNumber is already in your contact....");
			main.subMenu(user);
		}
	}
	
	public void viewContact(User user) {
		
	}

}
