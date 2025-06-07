package br.com.caioWd.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String description) {
}
