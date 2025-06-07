package br.com.caioWd.execption;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String description) {
}
