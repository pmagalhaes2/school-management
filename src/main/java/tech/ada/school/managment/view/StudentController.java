package tech.ada.school.managment.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.school.managment.domain.dto.v1.StudentDTO;
import tech.ada.school.managment.domain.exceptions.NotFoundException;
import tech.ada.school.managment.domain.service.student.IStudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@ControllerAdvice
public class StudentController {

    private final IStudentService service;

    @Autowired
    public StudentController(IStudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable("id") UUID id) throws NotFoundException {
        StudentDTO foundedStudent = service.getById(id);
        return ResponseEntity.ok().body(foundedStudent);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO student) {
        StudentDTO createdStudent = service.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") UUID id, @RequestBody @Valid StudentDTO student) throws NotFoundException {
        StudentDTO updatedStudent = service.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") UUID id) throws NotFoundException {
        service.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
