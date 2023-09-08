package Model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import valueObject.VLogin;
import valueObject.VUserInfo;

public class MAccount {
	public VUserInfo login(VLogin vlogin) throws FileNotFoundException {
		VUserInfo vuserinfo = null; //처음에는 비어있다.
		
		Scanner keyboard = new Scanner(new FileReader("data/Account.txt"));
		while(keyboard.hasNext()) {
			String line = keyboard.nextLine();
			String[] tokens = line.split(" "); // 한 라인을 띄어쓰기로 구분
			if(tokens[0].equals(vlogin.getUserID())) {
				if(tokens[1].equals(vlogin.getUserPW())) {
					vuserinfo = new VUserInfo();
					vuserinfo.setName(tokens[2]);
					vuserinfo.setMaxHakjum(Integer.parseInt(tokens[3]));
					vuserinfo.setUserID(tokens[0]);
					break;
				}
			}
			
		}
		return vuserinfo;
	}
	
	
}
