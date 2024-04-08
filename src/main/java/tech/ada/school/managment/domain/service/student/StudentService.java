package tech.ada.school.managment.domain.service.student;

import org.springframework.stereotype.Service;
import tech.ada.school.managment.domain.dto.v1.StudentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService implements IStudentService {

    public final List<StudentDTO> students = new ArrayList<>();

    @Override
    public StudentDTO createStudent(StudentDTO student) {
        StudentDTO newStudent = new StudentDTO(UUID.randomUUID(), student.getName());
        students.add(newStudent);
        return newStudent;
    }

    @Override
    public List<StudentDTO> getAll() {
        return students;
    }

    @Override
    public StudentDTO getById(UUID id) {
        Optional<StudentDTO> foundedStudent = students.stream().filter(s -> s.getId().equals(id)).findFirst();
        return foundedStudent.orElse(null);
    }

    @Override
    public StudentDTO updateStudent(UUID id, StudentDTO student) {
        StudentDTO foundedStudent = getById(id);
        students.remove(foundedStudent);
        final StudentDTO updatedStudent = new StudentDTO(id, student.getName());
        students.add(updatedStudent);
        return updatedStudent;
    }

    @Override
    public void deleteStudent(UUID id) {
        StudentDTO foundedStudent = getById(id);
        students.remove(foundedStudent);
    }
}
