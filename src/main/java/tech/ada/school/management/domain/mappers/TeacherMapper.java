package tech.ada.school.management.domain.mappers;

import tech.ada.school.management.domain.dto.v1.TeacherDTO;
import tech.ada.school.management.domain.entities.Teacher;

public class TeacherMapper {

    public static Teacher toEntity(TeacherDTO dto) {
        return new Teacher(
                dto.getCreatedAt(),
                dto.getId(),
                dto.getName());
    }

    public static TeacherDTO toDto(Teacher entity) {
        return new TeacherDTO(
                entity.getCreatedAt(),
                entity.getId(),
                entity.getName()
        );
    }
}
