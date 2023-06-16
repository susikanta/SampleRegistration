package com.sampleregistration.exception;

public class UploadException extends Exception {

	public UploadException(Throwable cause, int status) {
		super(cause);
		// this.status = status;
	}

	public UploadException(Throwable cause) {
		this(cause, -1);
	}
}
