package azi.foosball.api;

public class RestResponse {

	private final Integer code;
	private final String message;
	private final boolean successful;

	public RestResponse(Integer code, String message, boolean successful) {

		this.code = code;
		this.message = message;
		this.successful = successful;
	}

	public Integer getCode() {

		return code;
	}

	public String getMessage() {

		return message;
	}

	public boolean isSuccessful() {

		return successful;
	}
}
