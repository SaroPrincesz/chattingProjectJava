package chattingProjectJava;

import java.util.ArrayList;
import java.util.List;

public class ChattingApp {
	//ftdytuioupi[
	static Util server = Util.getObject();
	static ChattingApp main = new ChattingApp();
	static List<User> chattingAppUsers = new ArrayList<User>();

	
	public int getId() {
		return main.chattingAppUsers.size()+1;
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
	public boolean isExistPhoneNumber(long phoneNumber) {
		if (main.chattingAppUsers == null || main.chattingAppUsers.size() == 0) {
			return false;
		} else {
			for (int i = 0; i < main.chattingAppUsers.size(); i++) {
				if (main.chattingAppUsers.get(i).getPhoneNumber() == phoneNumber) {
					return true;
				}
			}
		}
		return false;
	}

//Add User Details
	public User addUserDetails(User user) {
		if (!isExistPhoneNumber(user.getPhoneNumber())) {
			main.chattingAppUsers.add(user);
			System.out.println("SignUp Successfully...!!!");
			return user;
		}
		return null;
	}

//SignUp
	public static User signUp() {
		// Get the UserName
		System.out.print("Enter User Name: ");
		String userName = null;
		int i = 0;
		for (; i < 2; i++) {
			userName = server.getString();
			if (!main.checkName(userName)) {
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
		for (; i < 2; i++) {
			phoneNumber = server.getLong();
			if (!main.checkPhoneNumber(phoneNumber)) {
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

		User user = new User(main.getId(), userName, phoneNumber, password);
		if (main.addUserDetails(user) == null) {
			System.out.println("This User is already Exist...");
			return null;
		} else
			return user;
	}
	
//Sub Menu method 
	public static void subMenu(User user) {
		
		System.out.println("1. Create Chat Contact \n2. View Chat Contact \n3. Chat \n4. Exit");
	}

//Main Menu method
	public static void mainMenu() {
		System.out.println("1. SignIn \n2. SignUp \n3. Exit");
		System.out.print("Enter Your Choice: ");
		int userInput = server.getInt();
		if (userInput == 2) {
			User user = signUp();
			if(user == null) {
				mainMenu();
			}else {
				subMenu(user);
			}
		}else if(userInput == 1) {
			
		}else{
			System.out.println("Main Menu Exit...!!!");
			return;
		}
	}

//Main method
	public static void main(String[] args) {
		System.out.println("-----Welcome-----");
		mainMenu();
	}

}
