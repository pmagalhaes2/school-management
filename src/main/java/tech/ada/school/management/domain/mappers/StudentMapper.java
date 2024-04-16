package tech.ada.school.management.domain.mappers;

import tech.ada.school.management.domain.dto.v1.StudentDTO;
import tech.ada.school.management.domain.entities.Student;

public class StudentMapper {

    public static Student toEntity(StudentDTO dto, String activity) {
        return new Student(
                dto.getCreatedAt(),
                dto.getId(),
                dto.getName(),
                dto.getCpf(),
                activity
        );
    }

    public static StudentDTO toDto(Student entity) {
        return new StudentDTO(
                entity.getCreatedAt(),
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getActivity()
        );
    }
}
