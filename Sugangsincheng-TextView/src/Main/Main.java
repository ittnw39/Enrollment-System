package Main;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import Presentation.PLogin;
import Presentation.PSugangsincheng;
import valueObject.VUserInfo;

public class Main{
	
	private Scanner keyboard;
	private PLogin pLogin;
	private PSugangsincheng vSugangsincheng;

	public Main(){
		
	}
	
	private void run() throws FileNotFoundException, UnsupportedEncodingException {
		keyboard= new Scanner(System.in);
		pLogin = new PLogin();
		
		VUserInfo vUserInfo = pLogin.login(keyboard);
		
		if(vUserInfo != null) {
			
			System.out.println(vUserInfo.getName() + ", 안녕하세요."); 
			vSugangsincheng = new PSugangsincheng();
			vSugangsincheng.run(vUserInfo, keyboard);
		}
		keyboard.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		Main main = new Main();
		main.run();

	}
}
