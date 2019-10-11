package chat;

public class InBoxDTO {
	
	private int chat_num; // 채팅방 번호 
	private String fromID;
	private String name;
	private int unreadMeassgeCount;
	private String chat_participants;
	
	
	
	public int getChat_num() {
		return chat_num;
	}
	public void setChat_num(int chat_num) {
		this.chat_num = chat_num;
	}
	public String getChat_participants() {
		return chat_participants;
	}
	public void setChat_participants(String chat_participants) {
		this.chat_participants = chat_participants;
	}
	public String getFromID() {
		return fromID;
	}
	public void setFromID(String fromID) {
		this.fromID = fromID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getUnreadMeassgeCount() {
		return unreadMeassgeCount;
	}
	public void setUnreadMeassgeCount(int unreadMeassgeCount) {
		this.unreadMeassgeCount = unreadMeassgeCount;
	}
	@Override
	public String toString() {
		return "InBoxDTO [chat_num=" + chat_num + ", fromID=" + fromID + ", name=" + name + ", unreadMeassgeCount="
				+ unreadMeassgeCount + ", chat_participants=" + chat_participants + "]";
	}
	
	

}
