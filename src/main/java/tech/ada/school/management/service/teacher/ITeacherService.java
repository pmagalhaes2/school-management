package tech.ada.school.management.service.teacher;

import tech.ada.school.management.domain.exceptions.NotFoundException;
import tech.ada.school.management.domain.dto.v1.TeacherDTO;

import java.util.List;
import java.util.UUID;

public interface ITeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    List<TeacherDTO> getAll();

    TeacherDTO getById(UUID id) throws NotFoundException;

    TeacherDTO updateTeacher(UUID id, TeacherDTO teacherDTO) throws NotFoundException;

    void deleteTeacher(UUID id) throws NotFoundException;
}
