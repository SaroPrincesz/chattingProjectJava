package chattingProjectJava;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private int groupId;
	private String groupName;
	private List<Integer> groupReceiverId;

	public Group(int groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
		groupReceiverId = new ArrayList<Integer>();
	}

	
}
