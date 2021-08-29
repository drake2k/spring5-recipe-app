package guru.springframework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UrlParameterNumberFormatException extends RuntimeException{
    public UrlParameterNumberFormatException() {
    }

    public UrlParameterNumberFormatException(String message) {
        super(message);
    }

    public UrlParameterNumberFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
