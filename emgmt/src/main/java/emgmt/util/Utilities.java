package emgmt.util;

public class Utilities {

	public boolean isNullorWhiteSpaces(String value){
		boolean flag = true;
		try {
			if (value != null || !value.trim().equals("")) {
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return flag;
	}
	
}
