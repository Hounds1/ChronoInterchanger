package io.chrono.interchange.global.error.message;

public enum ChronoInterChangeErrorMessage {
    INVALID_SET_UP_AUTO_LOCALE("CI-0001","The Auto Locale cannot be exists with other elements"),
    INVALID_DATE_FORMAT("CI-0002", "Date of parameter is not invalid format");

    private String code;
    private String message;

    ChronoInterChangeErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
