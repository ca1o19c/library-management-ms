package com.academy.librarymanagement.adapters.config.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"message", "status", "code", "path", "object_name", "errors"})
public class ErrorResponse {
    private final String message;
    private final int code;
    private final String status;
    private final String path;
    @JsonProperty("object_name")
    private String objectName;
    private List<ErrorObject> errors;

    public ErrorResponse(String message, int code, String status, String objectName, List<ErrorObject> errors, String path) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.objectName = objectName;
        this.errors = errors;
        this.path = path;
    }

    public ErrorResponse(String message, int code, String status, String path) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getObjectName() {
        return objectName;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }

    public String getPath() {
        return path;
    }
}
