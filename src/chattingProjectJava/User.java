package chattingProjectJava;

public class User {

	public int id;
	private String userName;
	private long phoneNumber;
	private String password;

	Util server = Util.getUtilObject();
	ChattingApp main = ChattingApp.getInstance();

//Default Constructor
	public User() {
	}
	
//For create User Object
	public User(int id, String userName, long phoneNumber, String password) {
		this.id = id;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

//For create Contact Object
	public User(int id, String contactName, long contactNumber) {
		this.id = id;
		userName = contactName;
		phoneNumber = contactNumber;
	}

// Get User Name
	public String getUserName() {
		return userName;
	}

// Get Phone Number
	public long getPhoneNumber() {
		return phoneNumber;
	}

//Add Group Members
	public void addGroupMember(Group group) {
		System.out.println("1. AddMember \n2. Exit");
		if (server.getByte() == 1) {
			System.out.print("Enter the Mobile Number: ");
			long mobileNumber = server.getLong();

			User user = main.isExistPhoneNumber(mobileNumber);
			if (user != null) {
				group.getGroupReceiversId().add(user.id);
				addGroupMember(group);
			} else {
				System.out.println("This PhoneNumber isn't use ChattingApp...");
				addGroupMember(group);
			}
		} else {
			System.out.println("Group Members Added Sucessfully...");
			return;
		}
	}
	
//is Exist group name
	public boolean isExistGroupName(String groupName) {
		if(server.getUsersGroup() == null || server.getUsersGroup().size() == 0) {
			return false;
		}else {
			for(int i=0; i<server.getUsersGroup().size(); i++) {
				if(server.getUsersGroup().get(i).getGroupName().equals(groupName)) {
					return true;
				}
			}
			return false;
		}
	}

//Create Group
	public void createNewGroup(User user) {
		System.out.print("Enter the Group Name: ");
		String groupName = server.getString();
		if(!isExistGroupName(groupName)) {
			Group group = new Group(main.getGroupId(), groupName, user.id);
			server.getUsersGroup().add(group);
			System.out.println("New Group Created...");
			addGroupMember(group);
			main.groupMenu(user);
		}else {
			System.out.println("This name is Already Exist... Please you use another name...");
			createNewGroup(user);
		}
	}

//is Exist Group Member
	public boolean isExistGroupMember(User user, Group group) {
		if (group.userId == user.id) {
			return true;
		} else if (group.getGroupReceiversId() != null && group.getGroupReceiversId().size() > 0) {
			for (int i = 0; i < group.getGroupReceiversId().size(); i++) {
				if (group.getGroupReceiversId().get(i) == user.id) {
					return true;
				}
			}
		}
		return false;
	}

//View Group
	public void viewGroup(User user) {
		if (server.getUsersGroup() == null || server.getUsersGroup().size() == 0) {
			System.out.println(" No Groups...");
		} else {
			for (int i = 0; i < server.getUsersGroup().size(); i++) {
				if (isExistGroupMember(user, server.getUsersGroup().get(i))) {
					System.out.println(server.getUsersGroup().get(i).getGroupName());
				}
			}
		}
		main.groupMenu(user);
	}

// create Contact
	public void createNewContact(User user) {
		System.out.print("Enter the Contact Name: ");
		String contactName = server.getString();

		System.out.print("Enter the Contact Number: ");
		long contactNumber = server.getLong();

		Contact contact = isExistContact(user.id, contactNumber);
		if (contact == null) {
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
	public Contact isExistContact(int id, long contactNumber) {
		if (server.getContactList() == null || server.getContactList().size() == 0) {
			return null;
		} else {
			for (int i = 0; i < server.getContactList().size(); i++) {
				if (server.getContactList().get(i).id == id
						&& server.getContactList().get(i).getPhoneNumber() == contactNumber) {
					return server.getContactList().get(i);
				}
			}
		}
		return null;
	}

// view Contact
	public void viewContact(User user) {
		if (server.getContactList() == null || server.getContactList().size() == 0) {
			System.out.println("No Contact...!!");
		} else {
			for (int i = 0; i < server.getContactList().size(); i++) {
				if (server.getContactList().get(i).id == user.id) {
					System.out.print("Contact Name: " + server.getContactList().get(i).getUserName());
					System.out.println("   Contact Number: " + server.getContactList().get(i).getPhoneNumber());
				}
			}
		}
		main.subMenu(user);
	}

}
