package tech.ada.school.managment.domain.dto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends Exception {
    private final Class clazz;
    private final String id;
}
