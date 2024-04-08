package tech.ada.school.managment.domain.service;

import tech.ada.school.managment.domain.dto.v1.TeacherDTO;

import java.util.List;
import java.util.UUID;

public interface ITeacherService {
    String createTeacher(String name);

    List<TeacherDTO> getAll();

    TeacherDTO getById(UUID id);

    void updateTeacher(UUID id, String name);

    void deleteTeacher(UUID id);
}
