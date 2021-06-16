package emgmt.common;

import emgmt.model.User;

public class response {
	private boolean success;
	public User getData() {
		return data;
	}
	public void setData(User data) {
		this.data = data;
	}
	private User data;
	
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
}
