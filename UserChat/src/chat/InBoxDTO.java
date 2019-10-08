package chat;

public class InBoxDTO {
	private String fromID;
	private String name;
	private int unreadMeassgeCount;
	
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
		return "InBoxDTO [fromID=" + fromID + ", name=" + name + ", unreadMeaasgeCount=" + unreadMeassgeCount + "]";
	}
	
	

}
