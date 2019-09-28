package azi.foosball;

class RestResponse {

	private final Integer code;
	private final String message;
	private final boolean successful;

	RestResponse(Integer code, String message, boolean successful) {

		this.code = code;
		this.message = message;
		this.successful = successful;
	}

	Integer getCode() {

		return code;
	}

	String getMessage() {

		return message;
	}

	boolean isSuccessful() {

		return successful;
	}
}
