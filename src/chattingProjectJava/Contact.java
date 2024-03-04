package chattingProjectJava;

public class Contact extends User {

	Util server = Util.getUtilObject();
	ChattingApp main = ChattingApp.getInstance();
	public int contactUserId;

//Default Constructor
	public Contact() {
	}

//Create new Contact Object
	public Contact(int id, String contactName, long contactNumber, int contactUserId) {
		super(id, contactName, contactNumber);
		this.contactUserId = contactUserId;
	}

}
