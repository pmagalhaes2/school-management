package tech.ada.school.managment.domain.service.student;

import tech.ada.school.managment.domain.dto.v1.StudentDTO;

import java.util.List;
import java.util.UUID;

public interface IStudentService {
    StudentDTO createStudent(StudentDTO studentDTO);

    List<StudentDTO> getAll();

    StudentDTO getById(UUID id);

    StudentDTO updateStudent(UUID id, StudentDTO studentDTO);

    void deleteStudent(UUID id);
}
