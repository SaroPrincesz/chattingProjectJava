package chattingProjectJava;

import java.util.Date;

public class Message {
	
	private int messageId;
	private String messageContent;
	private int senderId;
	private int receiveId;
	
//Constructor
	public Message(int messageId, String messageContent, int senderId, int receiveId) {
		this.messageId = messageId;
		this.messageContent = messageContent;
		this.senderId = senderId;
		this.receiveId = receiveId;
	}

	public int getMessageId() {
		return messageId;
	}

	public String getMessageContent() { 
		return messageContent;
	}

	public int getSenderId() {
		return senderId;
	}

	public int getReceiveId() {
		return receiveId;
	}

}
