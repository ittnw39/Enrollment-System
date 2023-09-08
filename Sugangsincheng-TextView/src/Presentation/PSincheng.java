package Presentation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import valueObject.VLecture;
import valueObject.VUserInfo;

public class PSincheng {
	private PLectureBasket pSinchengBasket;
	private PSugangsincheng pSugangsincheng;
	private VUserInfo vuserinfo;

	public PSincheng() {
		this.pSinchengBasket = new PLectureBasket();
	}

	public void goSincheng(VLecture vLecture, VUserInfo vuserinfo, Scanner keyboard) {
		loadSincheng(vuserinfo.getUserID(), vuserinfo); // 파일에서 수강신청 내용을 읽어옵니다.
		
	    if (pSinchengBasket.jungbok(vLecture)) {
	        System.out.println("이미 수강신청한 강좌입니다.");
	    } else if (pSinchengBasket.contains(vLecture.getCode())) {
	        System.out.println("이미 수강신청한 강좌입니다.");
	    } else {
	    	int newTotalCredits = vLecture.getCredit() + vuserinfo.getTotalCredits(); // 신청할 강좌의 학점과 기존 신청한 강좌들의 학점 합계
            int maxHakjum = vuserinfo.getMaxHakjum(); // 최대 수강 가능 학점

            if (newTotalCredits > maxHakjum) {
                System.out.println("수강 가능한 학점을 초과하였습니다... >>" );
                System.out.println("수강 가능 학점: " + (maxHakjum));
                System.out.println("현재 신청한 학점: " + vuserinfo.getTotalCredits());
                return;
            }
	        
	        pSinchengBasket.add(vLecture);
	        writeSinchengToFile(vuserinfo.getUserID(), "Sincheng", vLecture);
	        System.out.println("수강 신청 되었습니다. >> ");
	    }
	    showSincheng(keyboard, vuserinfo.getUserID(), vuserinfo);
	}
	
	
	
	public void loadSincheng(String userID, VUserInfo vuserinfo) {
		pSinchengBasket.clear();
		
	    String filename = "Sincheng/" + userID + "Sincheng.txt";
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(filename));
	        String line = null;
	        int totalCredits = 0; // 총 신청 학점을 저장하는 변수
	        
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(" ");
	            VLecture vLecture = new VLecture();
	            vLecture.setCode(Integer.parseInt(parts[0]));
	            vLecture.setName(parts[1]);
	            vLecture.setProfessor(parts[2]);
	            vLecture.setCredit(Integer.parseInt(parts[3]));
	            vLecture.setTime(parts[4]);
	            pSinchengBasket.add(vLecture);
	            
	            totalCredits += vLecture.getCredit();
	        }
	        reader.close();
	        vuserinfo.setTotalCredits(totalCredits);
	        
	       
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	   
	}

	private void writeSinchengToFile(String userID, String fileType, VLecture vLecture) {
		try {
			String filename = fileType + "/" + userID + fileType + ".txt";
	        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
	        
	        writer.write(vLecture.toString());
	        writer.newLine();  // 줄바꿈 문자 추가
	        
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showSincheng(Scanner keyboard,  String userID, VUserInfo vuserinfo) {
		pSinchengBasket.show();
		
		System.out.println("1.삭제 2.메뉴 9.종료");

        String aCode = keyboard.next();
        int bCode = Integer.parseInt(aCode);

        switch (bCode) {
            case 1: //삭제 기능으로
                deleteLecture(keyboard, userID);
                break;
            case 2: //메뉴로
            	 PSugangsincheng pSugangsincheng = new PSugangsincheng();
                 pSugangsincheng.run(vuserinfo, keyboard);
            	return;
            case 9:
                System.exit(0);
                break;
            default:
                break;
        }
      
	}
	

	private void deleteLecture(Scanner keyboard, String userID) {
		while (true) {
            System.out.print("삭제할 강좌 코드를 입력하세요: ");
            int lectureCode = keyboard.nextInt();
            if (pSinchengBasket.contains(lectureCode)) {
            	
                pSinchengBasket.remove(lectureCode);
                deleteLectureFromFile(userID, lectureCode);
                
                System.out.println("강좌가 삭제되었습니다. >> " + lectureCode);
                
                System.out.println("신청 목록 >>");
                pSinchengBasket.show();
                break;
            } else {
                System.out.println("해당 강좌 코드가 존재하지 않습니다. 다시 입력하세요.");
            }
        }       
	}

	private void deleteLectureFromFile(String userID, int lectureCode) {
		String filename = "Sincheng/" + userID + "Sincheng.txt";
        File file = new File(filename);
        Path path = file.toPath();

        try {
            String content = Files.readString(path);

            // 강좌 코드로 삭제할 라인 찾기
            String deletedLine = "";
            String[] lines = content.split("\n");
            for (String line : lines) {
                String[] parts = line.split(" ");
                if (Integer.parseInt(parts[0]) != lectureCode) {
                    deletedLine += line + "\n";
                }
            }

            Files.writeString(path, deletedLine, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
