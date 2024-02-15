package chattingProjectJava;

import java.util.Scanner;

public class Util {

	Scanner scanner = new Scanner(System.in);
//Singleton Object
	private static Util util;

//Get Object
	public static Util getObject() {
		if (util == null) {
			util = new Util();
		}
		return util;
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
