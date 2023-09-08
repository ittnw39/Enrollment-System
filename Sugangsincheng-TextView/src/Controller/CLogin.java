package Controller;
import java.io.FileNotFoundException;

import Model.MAccount;
import valueObject.VLogin;
import valueObject.VUserInfo;

public class CLogin { //v를 위한 전용 워커.

	public VUserInfo login(VLogin vlogin) throws FileNotFoundException {
		// TODO Auto-generated method stub
		MAccount maccount = new MAccount();
		VUserInfo vuserinfo = maccount.login(vlogin);
		return vuserinfo;
	}

}
