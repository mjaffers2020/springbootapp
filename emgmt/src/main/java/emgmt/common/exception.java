package emgmt.common;

public class exception {
	private String message;
	private String type;
	private String method;
	private int linno;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getLinno() {
		return linno;
	}
	public void setLinno(int linno) {
		this.linno = linno;
	}

}
