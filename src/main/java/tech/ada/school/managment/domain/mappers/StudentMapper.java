package tech.ada.school.managment.domain.mappers;

import tech.ada.school.managment.domain.dto.v1.StudentDTO;
import tech.ada.school.managment.domain.entities.Student;

public class StudentMapper {

    public static Student toEntity(StudentDTO dto) {
        return new Student(
                dto.getCreatedAt(),
                dto.getId(),
                dto.getName()
        );
    }

    public static StudentDTO toDto(Student entity) {
        return new StudentDTO(
                entity.getCreatedAt(),
                entity.getId(),
                entity.getName()
        );
    }
}
