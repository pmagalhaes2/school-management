package tech.ada.school.management.service.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.school.management.domain.dto.v1.StudentDTO;
import tech.ada.school.management.domain.entities.Student;
import tech.ada.school.management.domain.exceptions.NotFoundException;
import tech.ada.school.management.domain.mappers.StudentMapper;
import tech.ada.school.management.repositories.StudentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student newStudent = StudentMapper.toEntity(studentDTO);
        repository.save(newStudent);
        return StudentMapper.toDto(newStudent);
    }

    @Override
    public List<StudentDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(StudentMapper::toDto)
                .toList();
    }

    @Override
    public StudentDTO getById(UUID id) throws NotFoundException {
        return StudentMapper.toDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(StudentDTO.class, String.valueOf(id))));
    }

    @Override
    @Transactional
    public StudentDTO updateStudent(UUID id, StudentDTO studentDTO) throws NotFoundException {
        StudentDTO foundedStudent = getById(id);
        foundedStudent.setName(studentDTO.getName());
        foundedStudent.setCpf(studentDTO.getCpf());
        return StudentMapper.toDto(repository.save(StudentMapper.toEntity(foundedStudent)));
    }

    @Override
    @Transactional
    public void deleteStudent(UUID id) throws NotFoundException {
        StudentDTO foundedStudent = getById(id);
        repository.delete(StudentMapper.toEntity(foundedStudent));
    }
}
