package tech.ada.school.managment.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.managment.domain.dto.v1.StudentDTO;
import tech.ada.school.managment.domain.service.student.IStudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final IStudentService service;

    @Autowired
    public StudentController(IStudentService service) {
        this.service = service;
    }

    @GetMapping
    List<StudentDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    StudentDTO getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO student) {
        StudentDTO createdStudent = service.createStudent(student);
        if (createdStudent != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") UUID id, @RequestBody @Valid StudentDTO student) {
        StudentDTO updatedStudent = service.updateStudent(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable("id") UUID id) {
        service.deleteStudent(id);
    }
}
