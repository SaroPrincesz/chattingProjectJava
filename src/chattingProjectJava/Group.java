package chattingProjectJava;

import java.util.ArrayList;
import java.util.List;

public class Group {
	public int groupId;
	private String groupName;
	private List<Integer> groupReceiversId;

	public Group(int groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
		groupReceiversId = new ArrayList<Integer>();
	}

	public String getGroupName() {
		return groupName;
	}
	
	public List<Integer> getGroupReceiversId(){
		return groupReceiversId;
	}
	
}
