package valueObject;

public class VLogin { //value object
	
	private String userID;
	private String userPW;
	
	public void setUserID(String userID) { //외부에서 전달한 값을 집어넣음.
		this.userID = userID;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserPW() {
		return userPW;
	}

}
