package Presentation;

import java.util.Scanner;
import java.util.Vector;

import Controller.CIndex;
import Controller.CLecture;
import valueObject.VIndex;
import valueObject.VLecture;
import valueObject.VUserInfo;

public class PLectureSelection {

	private CIndex cIndex;
	private CLecture cLecture;
	
	public PLectureSelection() {
		this.cIndex = new CIndex();
		this.cLecture = new CLecture();
	}

	private String findIndex(String message, String fileName, Scanner keyboard) {
		System.out.println(message + "코드를 입력하세요.");
		Vector<VIndex> vIndexVector =  cIndex.getVIndexVector(fileName);
		for (VIndex vIndex: vIndexVector) {
			vIndex.show();
		}
		
		String sCode = keyboard.next();
		int iCode = Integer.parseInt(sCode);
		
		 String selectedFileName = null;
		    for (VIndex vIndex: vIndexVector) {
		        if (vIndex.getCode() == iCode) {
		            selectedFileName = vIndex.getFilename();
		            break;
		        }
		    }
		    
		    if (selectedFileName == null) {
		        System.out.println("잘못된 코드가 입력되었습니다.");
		    }
		    
		    return selectedFileName;
	}
	
	public VLecture findLecture(String message, String fileName, Scanner keyboard) {
		System.out.println(message + "코드를 입력하세요.");
		Vector<VLecture> vLectureVector =  cLecture.getVLectureVector(fileName);
		for (VLecture vLecture: vLectureVector) {
			vLecture.show();
		}
		
		String sCode = keyboard.next();
		int iCode = Integer.parseInt(sCode);
		VLecture selectedVLecture = null;
	    for (VLecture vLecture: vLectureVector) {
	        if (vLecture.getCode() == iCode) {
	            selectedVLecture = vLecture;
	            break;
	        }
	    }

	    if (selectedVLecture == null) {
	        System.out.println("잘못된 코드가 입력되었습니다.");
	    }

	    return selectedVLecture;
	}
	
	public VLecture selectLecture(VUserInfo vUserinfo, Scanner keyboard) {
		String campusFileName = this.findIndex("캠퍼스", "root", keyboard);
		String collegeFileName = this.findIndex("대학", campusFileName, keyboard);
		String departmentFileName = this.findIndex("학과", collegeFileName, keyboard);
		
		VLecture vLecture = this.findLecture("강좌", departmentFileName, keyboard);
		
		return vLecture;
	}
}
