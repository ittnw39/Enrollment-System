package Presentation;

import java.io.FileNotFoundException;
import java.util.Scanner;
import Controller.CLogin;
import valueObject.VLogin;
import valueObject.VUserInfo;

public class PLogin { // presentation 화면

	public VUserInfo login(Scanner keyboard) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in); // 스캐너랑 키보드 연결. 다 쓰면 돌려줘야 함. 독점적으로 내가 쓸 수 있어야 함.

		System.out.println("아이디를 입력하세요.");
		String userID = sc.next();
		System.out.println("비밀번호를 입력하세요.");
		String userPW = sc.next();

		int loginsido = 0;
		while (loginsido < 5) {
			VLogin vlogin = new VLogin(); // value object에 담기
			vlogin.setUserID(userID); // vlogin에 아이디 담기
			vlogin.setUserPW(userPW); // vlogin에 비밀번호 담기

			CLogin clogin = new CLogin();
			VUserInfo vuserinfo = clogin.login(vlogin);

			if (vuserinfo == null) {
				
				System.out.println("잘못 입력되었습니다. 다시 시도해주세요. (남은 시도 횟수: " + (5 - loginsido) + ")");
				loginsido++;
				System.out.println("아이디를 입력하세요.");
				userID = sc.next();
				System.out.println("비밀번호를 입력하세요.");
				userPW = sc.next();
				
			} else {
				return vuserinfo;
			}
		}
		
		System.out.println("로그인 시도 횟수를 초과하여 더 이상 로그인이 불가합니다..");
		return null;
	}
}
