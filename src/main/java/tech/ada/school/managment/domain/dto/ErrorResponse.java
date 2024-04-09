package tech.ada.school.managment.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tech.ada.school.managment.domain.dto.exceptions.NotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class ErrorResponse {
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<ErrorMessage> errors;

    private ErrorResponse(String message) {
        this.message = message;
    }

    private ErrorResponse(String message, Collection<ErrorMessage> errors) {
        this(message);
        this.errors = errors;
    }

    public static ErrorResponse createFromException(NotFoundException e) {
        String message = "No record of " + e.getClazz().getSimpleName() + " found for id " + e.getId();
        return new ErrorResponse(message);
    }


    public static ErrorResponse createFromException(MethodArgumentNotValidException e) {
        var violations = e.getFieldErrors().stream().map(violation -> new ErrorMessage(violation.getField(), violation.getDefaultMessage())).collect(Collectors.toList());
        return new ErrorResponse("Violation errors", violations);
    }
}
