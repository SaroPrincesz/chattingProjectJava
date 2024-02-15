package chattingProjectJava;

import java.util.Date;

public class Message {
	private int messageId;
	private String messageContent;
	private int senderId;
	private int receiveId;
	private Date date;
//	private int receivedUserId;
//	private int receivedGroupId;

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

	public Date getDate() {
		return date;
	}

}
