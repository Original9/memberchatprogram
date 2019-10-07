package chat;

public class InBoxDTO {
	private String fromID;
	private String name;
	private int unreadMeaasgeCount;
	
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
	public int getUnreadMeaasgeCount() {
		return unreadMeaasgeCount;
	}
	public void setUnreadMeaasgeCount(int unreadMeaasgeCount) {
		this.unreadMeaasgeCount = unreadMeaasgeCount;
	}
	
	

}
