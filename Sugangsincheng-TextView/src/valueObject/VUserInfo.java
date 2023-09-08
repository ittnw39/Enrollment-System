package valueObject;

public class VUserInfo { //account에서 돌아오는 정보
	private String name;
	private String userID;
	private int maxHakjum;
	private int totalCredits;
	
	public int getMaxHakjum() {
		return maxHakjum;
	}

	public void setMaxHakjum(int maxHakjum) {
		this.maxHakjum = maxHakjum;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		// TODO Auto-generated method stub
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
		
	}
	
	public int getTotalCredits() {
		return totalCredits;
	}
}