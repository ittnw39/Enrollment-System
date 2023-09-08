package Presentation;

import java.util.Vector;

import valueObject.VLecture;

public class PLectureBasket {

	Vector<VLecture> vLectureVector;
	
	public PLectureBasket() {
		this.vLectureVector = new Vector<VLecture>();
	}

	public void add(VLecture vLecture) {
		 if (jungbok(vLecture)) {
		        return;
		    }
		this.vLectureVector.add(vLecture);
	}
	
	
	public boolean jungbok(VLecture vLecture) {
	    for (VLecture existingLecture : vLectureVector) {
	        if (existingLecture.getCode() == vLecture.getCode()
	                && existingLecture.getName().equals(vLecture.getName())
	                && existingLecture.getTime().equals(vLecture.getTime())) {
	            return true;
	        }
	    }
	    return false;
	}

	public void show() {
		for(VLecture vLecture: vLectureVector) {
			vLecture.show();
		}
	}

	public void remove(int lectureCode) {
		for (int i = 0; i < vLectureVector.size(); i++) {
	        VLecture vLecture = vLectureVector.get(i);
	        if (vLecture.getCode() == lectureCode) {
	        	vLectureVector.remove(i);
	            return;
	        }
	    }
	    System.out.println("해당 강좌 코드가 존재하지 않습니다.");
		
	}

	public void clear() {
		this.vLectureVector.clear();
		
	}

	public boolean contains(int lectureCode) {
		for (VLecture vLecture : vLectureVector) {
	        if (vLecture.getCode() == lectureCode) {
	            return true;
	        }
	    }
	    return false;
	}

	public VLecture get(int lectureCode) {
		for (VLecture vLecture : vLectureVector) {
			if (vLecture.getCode() == lectureCode) {
				return vLecture;
			}
		}
		return null;
	}

	public int indexOf(VLecture vLecture) {
		return vLectureVector.indexOf(vLecture);
	}

	
}
