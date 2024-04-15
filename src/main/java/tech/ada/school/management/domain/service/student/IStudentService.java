package tech.ada.school.management.domain.service.student;

import tech.ada.school.management.domain.dto.v1.StudentDTO;
import tech.ada.school.management.domain.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface IStudentService {
    StudentDTO createStudent(StudentDTO studentDTO);

    List<StudentDTO> getAll();

    StudentDTO getById(UUID id) throws NotFoundException;

    StudentDTO updateStudent(UUID id, StudentDTO studentDTO) throws NotFoundException;

    void deleteStudent(UUID id) throws NotFoundException;
}
