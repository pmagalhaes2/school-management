package tech.ada.school.managment.domain.service.teacher;

import tech.ada.school.managment.domain.dto.exceptions.NotFoundException;
import tech.ada.school.managment.domain.dto.v1.TeacherDTO;

import java.util.List;
import java.util.UUID;

public interface ITeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    List<TeacherDTO> getAll();

    TeacherDTO getById(UUID id) throws NotFoundException;

    TeacherDTO updateTeacher(UUID id, TeacherDTO teacherDTO) throws NotFoundException;

    void deleteTeacher(UUID id) throws NotFoundException;
}
