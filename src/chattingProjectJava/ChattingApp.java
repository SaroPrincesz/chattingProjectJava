package chattingProjectJava;

import java.util.ArrayList;
import java.util.List;

public class ChattingApp {

	static int messageId = 2000;
	static int groupId = 1000;

//Chatting App User's List
	public List<User> chattingAppUsers = new ArrayList<User>();

//Object
	Util server = Util.getUtilObject();
	Contact contact = new Contact();
	private static ChattingApp main = new ChattingApp();

//Get Object 
	public static ChattingApp getInstance() {
		return main;
	}

//Get Id
	public int getId() {
		return chattingAppUsers.size() + 1;
	}

//Get Message Id
	public int getMessageId() {
		return ++messageId;
	}

//Get Group Id
	public int getGroupId() {
		return ++groupId;
	}

// Check Name
	public boolean checkName(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isAlphabetic(name.charAt(i)) && name.charAt(i) != ' ') {
				return false;
			}
		}
		return true;
	}

// Check Phone Number
	public boolean checkPhoneNumber(long tempPhone) {
		long rev = 0;
		int count = 0;
		while (tempPhone != 0) {
			rev = tempPhone % 10;
			tempPhone /= 10;
			count++;
		}
		if (count == 10) {
			return true;
		}
		return false;
	}

//is Exist phoneNumber
	public User isExistPhoneNumber(long phoneNumber) {
		if (chattingAppUsers == null || chattingAppUsers.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < chattingAppUsers.size(); i++) {
				if (chattingAppUsers.get(i).getPhoneNumber() == phoneNumber) {
					return chattingAppUsers.get(i);
				}
			}
		}
		return null;
	}

//Group Menu
	public void groupMenu(User user) {
		System.out.println("1. Create Group \n2. View Group \n3. Chat \n4. Exit");
		System.out.print("Enter your choice: ");
		byte userInput = server.getByte();

		if (userInput == 1) {
			user.createNewGroup(user);
		} else if (userInput == 2) {
			user.viewGroup(user);
		} else if (userInput == 3) {
			Group group = chooseTheGroup(user);
			if (group == null) {
				user.viewGroup(user);
			} else {
				groupMessageMenu(group, user);
			}
		} else {
			System.out.println("Group Menu Exit...");
			subMenu(user);
			return;
		}
	}

//group Message Menu
	public void groupMessageMenu(Group group, User user) {
		System.out.println("1. Message \n2. View Old Chat \n3. Exit");
		System.out.print("Enter your Choice: ");
		byte userInput = server.getByte();

		if (userInput == 1) {
			System.out.println("If you want to close that chat, Enter 'Mmm' ");
			message(user, group);
			groupMessageMenu(group, user);
		} else if (userInput == 2) {
			loadingGroupMessages(group, user);
		} else {
			System.out.println("Group Message Menu Exit...");
			groupMenu(user);
			return;
		}
	}

// Get the phonenumber
	public long getUser(int id) {
		if (chattingAppUsers == null || chattingAppUsers.size() == 0) {
			return 0;
		} else {
			for (int i = 0; i < chattingAppUsers.size(); i++) {
				if (chattingAppUsers.get(i).id == id) {
					return chattingAppUsers.get(i).getPhoneNumber();
				}
			}
		}
		return 0;
	}

//View Group old Chats
	public void viewGroupOldChat(Group group, User user, List<Message> loadMessages) {
		for (int i = 0; i < loadMessages.size(); i++) {
			if (loadMessages.get(i).getSenderId() == user.id) {
				System.out.println("\t\t\t\t" + loadMessages.get(i).getMessageContent());
			} else {
				long phoneNumber = getUser(loadMessages.get(i).getSenderId());
				Contact contact = user.isExistContact(user.id, phoneNumber);
				if (contact != null) {
					System.out.println(contact.getUserName() + " : " + loadMessages.get(i).getMessageContent());
				} else {
					System.out.println(phoneNumber + " : " + loadMessages.get(i).getMessageContent());
				}
			}
		}
		groupMessageMenu(group, user);
	}

//Loading Group Messages
	public void loadingGroupMessages(Group group, User user) {
		List<Message> loadMessages = new ArrayList<Message>();
		if (server.getUserMessages() == null || server.getUserMessages().size() == 0) {
			System.out.println("No Messages...");
		} else {
			for (int i = 0; i < server.getUserMessages().size(); i++) {
				if (server.getUserMessages().get(i).getReceiveId() == group.groupId) {
					loadMessages.add(server.getUserMessages().get(i));
				}
			}
			if (loadMessages.size() > 0) {
				viewGroupOldChat(group, user, loadMessages);
			} else {
				System.out.println("No Messages...");
				groupMessageMenu(group, user);
				return;
			}
		}
	}

// View Old Individual Chats
	public void viewOldChat(Contact contact, User user) {
		int count = 0;
		if (server.getUserMessages() == null || server.getUserMessages().size() == 0) {
			System.out.println("No Messages...");
		} else {
			for (int i = 0; i < server.getUserMessages().size(); i++) {
				if (server.getUserMessages().get(i).getSenderId() == user.id
						&& server.getUserMessages().get(i).getReceiveId() == contact.contactUserId) {
					System.out.println("\t\t" + server.getUserMessages().get(i).getMessageContent());
					count++;
				} else if (server.getUserMessages().get(i).getSenderId() == contact.contactUserId
						&& server.getUserMessages().get(i).getReceiveId() == user.id) {
					System.out.println(server.getUserMessages().get(i).getMessageContent());
					count++;

				}
			}
			System.out.println();
		}
		if (count == 0) {
			System.out.println("No Messages...");
		}
		messageMenu(contact, user);
	}

//User Enter Message
	public void message(User user, Contact contact) {
		System.out.print("Message: ");
		String msg = server.getString();
		if (msg.equals("Mmm")) {
			return;
		} else {
			server.getUserMessages().add(new Message(getMessageId(), msg, user.id, contact.contactUserId));
			message(user, contact);
		}
	}

//Method Overloading
	public void message(User user, Group group) {
		System.out.print("Message: ");
		String msg = server.getString();
		if (msg.equals("Mmm")) {
			return;
		} else {
			server.getUserMessages().add(new Message(getMessageId(), msg, user.id, group.groupId));
			message(user, group);
		}
	}

// Message Menu
	public void messageMenu(Contact contact, User user) {
		System.out.println("1. Message \n2. View Old Chat \n3. Exit");
		byte userInput = server.getByte();
		if (userInput == 1) {
			System.out.println("If you want to close that chat, Enter 'Mmm' ");
			message(user, contact);
			messageMenu(contact, user);
		} else if (userInput == 2) {
			viewOldChat(contact, user);
		} else {
			System.out.println("Message Menu Exit...");
			subMenu(user);
			return;
		}

	}

//Choose the Contact from the User
	public Group chooseTheGroup(User user) {
		System.out.print("Enter the Group Name: ");
		String groupName = server.getString();
		if (server.getUsersGroup() == null || server.getUsersGroup().size() == 0) {
			System.out.println("No Groups...");
			return null;
		} else {
			for (int i = 0; i < server.getUsersGroup().size(); i++) {
				if (server.getUsersGroup().get(i).getGroupName().equals(groupName)) {
					if (server.getUsersGroup().get(i).userId == user.id) {
						return server.getUsersGroup().get(i);
					} else {
						for (int j = 0; j < server.getUsersGroup().get(i).getGroupReceiversId().size(); j++) {
							if (server.getUsersGroup().get(i).getGroupReceiversId().get(j) == user.id) {
								return server.getUsersGroup().get(i);
							}
						}
					}
				}
			}
			return null;
		}
	}

//Choose the Contact from the User
	public Contact chooseTheContact(User user) {
		System.out.print("Enter the Contact Name: ");
		String contactName = server.getString();
		if (server.getContactList() == null || server.getContactList().size() == 0) {
			System.out.println("No contact");
			return null;
		} else {
			for (int i = 0; i < server.getContactList().size(); i++) {
				if (server.getContactList().get(i).id == user.id
						&& server.getContactList().get(i).getUserName().equals(contactName)) {
					return server.getContactList().get(i);
				}
			}
			return null;
		}
	}

//subMenu
	public void subMenu(User user) {
		System.out.println("1. Create Chat Contact \n2. View Chat Contact \n3. Chat \n4. Group \n5.Exit");
		System.out.print("Enter you Choice: ");
		byte userInput = server.getByte();

		if (userInput == 1) {
			user.createNewContact(user);
		} else if (userInput == 2) {
			user.viewContact(user);
		} else if (userInput == 3) {
			Contact contact = chooseTheContact(user);
			if (contact == null) {
				user.viewContact(user);
			} else {
				messageMenu(contact, user);
			}
		} else if (userInput == 4) {
			groupMenu(user);
		} else {
			System.out.println("Sub Menu Exit...");
			mainMenu();
			return;
		}
	}

//SignUp
	public User signUp() {
		// Get the UserName
		System.out.print("Enter User Name: ");
		String userName = null;
		int i = 0;
		for (; i < 3; i++) {
			userName = server.getString();
			if (!checkName(userName)) {
				System.out.println("Please enter you correct name...");
			} else {
				break;
			}
		}
		if (i == 2) {
			return null;
		}
		i = 0;

		// Get the phone Number
		System.out.print("Enter Phone Number: ");
		long phoneNumber = 0;
		for (; i < 3; i++) {
			phoneNumber = server.getLong();
			if (!checkPhoneNumber(phoneNumber)) {
				System.out.println("Please enter the correct phone Number...");
			} else {
				break;
			}
		}
		if (i == 2) {
			return null;
		}

		// Get the password
		System.out.print("Enter Password: ");
		String password = server.getString();

		// User Object
		User user = new User(main.getId(), userName, phoneNumber, password);

		// Add User Details
		if (isExistPhoneNumber(phoneNumber) == null) {
			chattingAppUsers.add(user);
			System.out.println("SignUp Successfully...!!!");
			return user;
		}
		return null;
	}

//signIn
	public User signIn() {
		// Get the userName
		System.out.print("Enter userName: ");
		String userName = server.getString();

		// Get phoneNumber
		System.out.print("Enter phoneNumber: ");
		long phoneNumber = server.getLong();

		// Get password
		System.out.print("Enter password: ");
		String password = server.getString();

		return main.isExistPhoneNumber(phoneNumber);
	}

//mainMenu
	public void mainMenu() {
		System.out.println("1. SignIn \n2. SignUp \n3. Exit");
		System.out.print("Enter Your Choice: ");
		byte userInput = server.getByte();

		if (userInput == 1) {
			User user = signIn();
			if (user == null) {
				System.out.println("Please enter correct userName, phoneNumber and password");
				mainMenu();
			} else {
				subMenu(user);
			}
		} else if (userInput == 2) {
			User user = signUp();
			if (user == null) {
				System.out.println("This user is already Exist...");
				mainMenu();
			} else {
				subMenu(user);
			}
		} else {
			System.out.println("Main Menu Exit...!!!");
			return;
		}
	}

//Main method
	public static void main(String[] args) {
		System.out.println("-----Welcome-----");
		main.mainMenu();
	}

}
