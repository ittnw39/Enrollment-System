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

public class PMiridamgi {
	private PLectureBasket pMiridamgiBasket;
	private PSincheng pSincheng;

	public PMiridamgi() {
		this.pMiridamgiBasket = new PLectureBasket();
		this.pSincheng = new PSincheng();
	}
	
	public void goBasket(VUserInfo vuserinfo, VLecture vLecture, Scanner keyboard) {
		
		loadBasket(vuserinfo.getUserID(), vuserinfo); // 파일에서 수강신청 내용을 읽어옴

	    if (pMiridamgiBasket.jungbok(vLecture)) {
	        System.out.println("이미 미리담기한 강좌입니다.");
	    } else if (pMiridamgiBasket.contains(vLecture.getCode())) {
	        System.out.println("이미 미리담기한 강좌입니다.");
	    } else {
	    	int newTotalCredits = vLecture.getCredit() + vuserinfo.getTotalCredits(); // 미리담기할 강좌의 학점과 장바구니 속 학점의 합
            int maxHakjum = vuserinfo.getMaxHakjum() + 6; // 최대 미리담기 가능한 학점

            if (newTotalCredits > maxHakjum) {
                System.out.println("미리담기 가능한 학점을 초과하였습니다... >>");
                System.out.println("장바구니 최대 학점: " + (maxHakjum));
                System.out.println("현재 담긴 학점: " + vuserinfo.getTotalCredits());
                return;
            }

	        pMiridamgiBasket.add(vLecture);
	        writeToFile(vuserinfo.getUserID(), "Basket", vLecture);
	        System.out.println("미리 담기 되었습니다. >> " + vLecture);
	    }
	    
		   while (true) {
        System.out.println("1.신청 2.삭제 3.메뉴 9. 종료");

		String aCode = keyboard.next();
		int bCode = Integer.parseInt(aCode);

		switch (bCode) {
		case 1: // 그대로 수강신청하기
			if (pMiridamgiBasket.contains(vLecture.getCode())) {
                pSincheng.goSincheng(vLecture, vuserinfo, keyboard);
            } else {
                System.out.println("해당 강좌 코드가 존재하지 않습니다. 다시 입력하세요.");
            }
            break;
		case 2:
			deleteFromBasket(vuserinfo.getUserID(), vLecture.getCode());
			System.out.println("강좌가 장바구니에서 삭제되었습니다. >> " + vLecture.getCode());
			vLecture = null;
			break;
		case 3: // 강좌선택메뉴로
			vLecture = null;
            return;
		case 9:
			System.exit(0);
			break;
		default:
			break;
		}
        }
	}

	public void loadBasket(String userID, VUserInfo vuserinfo) {//파일에서 읽어오는 부분
		 pMiridamgiBasket.clear();
		
	    String filename = "Basket/" + userID + "Basket.txt";
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(filename));
	        String line = null;
	        int totalCredits = 0; 
	        
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(" ");
	            VLecture vLecture = new VLecture();
	            vLecture.setCode(Integer.valueOf(parts[0]));
	            vLecture.setName(parts[1]);
	            vLecture.setProfessor(parts[2]);
	            vLecture.setCredit(Integer.valueOf(parts[3]));
	            vLecture.setTime(parts[4]);
	            pMiridamgiBasket.add(vLecture);
	            
	            totalCredits += vLecture.getCredit();
	        }
	        reader.close();
	        
	        vuserinfo.setTotalCredits(totalCredits);
	       
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	   
	}
	
	
	public void writeToFile(String userID, String fileType, VLecture addedLecture) {
		try {
			String filename = fileType + "/" + userID + fileType + ".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
			

	        writer.write(addedLecture.toString());
	        writer.newLine();  // 줄바꿈 문자 추가
	        
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void showBasket(Scanner keyboard, String userID, VLecture vLecture, VUserInfo vuserinfo) {
		pMiridamgiBasket.show();
		
		while (true) {
			System.out.println("1.신청 2.삭제 3.메뉴 9.종료");

			String aCode = keyboard.next();
			int bCode = Integer.parseInt(aCode);

			switch (bCode) {
				case 1: // 그대로 수강신청하기
					System.out.print("신청할 강좌 코드를 입력하세요: ");
	                int lectureCode = keyboard.nextInt();
	                VLecture selectedLecture = pMiridamgiBasket.get(lectureCode);
	                if (selectedLecture != null) {
	                    pSincheng.goSincheng(selectedLecture, vuserinfo, keyboard);
	                } else {
	                    System.out.println("해당 강좌 코드가 존재하지 않습니다. 다시 입력하세요.");
	                }
	                break;
				case 2: // 장바구니에서 삭제
					System.out.print("삭제할 강좌 코드를 입력하세요: ");
					int deleteCode = keyboard.nextInt();
					if (pMiridamgiBasket.contains(deleteCode)) {
						pMiridamgiBasket.remove(deleteCode);
						deleteFromBasket(userID, deleteCode);
						System.out.println("강좌가 삭제되었습니다. >> " + deleteCode);
						System.out.println("장바구니 목록 >>");
						pMiridamgiBasket.show();
					} else {
						System.out.println("해당 강좌 코드가 존재하지 않습니다. 다시 입력하세요.");
					}
					break;
				case 3: // 메뉴로	
	                return;
				case 9:
					System.exit(0);
					break;
				default:
					break;
			}
		}
	}
	
		private void deleteFromBasket(String userID, int lectureCode) {
			String filename = "Basket/" + userID + "Basket.txt";
			File file = new File(filename);
			Path path = file.toPath();

			try {
				// 파일의 모든 라인을 읽어옵니다.
				String content = Files.readString(path);

				// 강좌 코드로 삭제할 라인을 찾습니다.
				String deletedLine = "";
				String[] lines = content.split("\n");
				for (String line : lines) {
					String[] parts = line.split(" ");
					if (Integer.parseInt(parts[0]) != lectureCode) {
						deletedLine += line + "\n";
					}
				}

				// 삭제된 라인을 파일에 다시 씁니다.
				Files.writeString(path, deletedLine, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}

