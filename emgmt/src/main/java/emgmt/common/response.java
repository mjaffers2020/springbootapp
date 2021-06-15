package emgmt.common;

public class response {
	private boolean success;
	private String data;
	private exception exception;
	public exception getException() {
		return exception;
	}
	public void setException(exception exception) {
		this.exception = exception;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
