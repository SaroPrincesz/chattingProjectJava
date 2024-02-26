package chattingProjectJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {

//Singleton Object
	private static Util util;

	Scanner scanner = new Scanner(System.in);
	private List<Contact> contactList = new ArrayList<Contact>();
	private List<Message> userMessages = new ArrayList<Message>();

//Get Object
	public static Util getUtilObject() {
		if (util == null) {
			util = new Util();
		}
		return util;
	}

//Get User Contact List
	public List<Contact> getContactList() {
		return contactList;
	}

//Get User Message List
	public List<Message> getUserMessages() {
		return userMessages;
	}

//Get Byte Value
	public byte getByte() {
		while (true) {
			if (scanner.hasNextByte()) {
				return scanner.nextByte();
			} else {
				scanner.nextLine();
			}
		}
	}

//Get Integer Value
	public int getInt() {
		while (true) {
			if (scanner.hasNextInt()) {
				return scanner.nextInt();
			} else {
				scanner.nextLine();
			}
		}
	}

//Get Long Value
	public long getLong() {
		while (true) {
			if (scanner.hasNextLong()) {
				return scanner.nextLong();
			} else {
				scanner.nextLine();
			}
		}
	}

//Get String Value
	public String getString() {
		while (true) {
			String str = scanner.nextLine();
			if (!str.equals("")) {
				return str;
			}
		}
	}

//Get Float Value
	public float getFloat() {
		while (true) {
			if (scanner.hasNextFloat()) {
				return scanner.nextFloat();
			} else {
				scanner.nextLine();
			}
		}
	}

//Get Double Value
	public double getDouble() {
		while (true) {
			if (scanner.hasNextDouble()) {
				return scanner.nextDouble();
			} else {
				scanner.nextLine();
			}
		}
	}

//Get Character Value
	public char getCharacter() {
		return scanner.nextLine().charAt(0);
	}

}
