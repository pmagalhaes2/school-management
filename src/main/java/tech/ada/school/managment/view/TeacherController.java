package tech.ada.school.managment.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.managment.domain.dto.v1.TeacherDTO;
import tech.ada.school.managment.domain.service.teacher.ITeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final ITeacherService service;

    private final List<TeacherDTO> teachers = new ArrayList<>();

    @Autowired
    public TeacherController(ITeacherService service) {
        this.service = service;
    }

    @GetMapping
    public List<TeacherDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TeacherDTO getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody @Valid TeacherDTO teacher) {
        TeacherDTO createdTeacher = service.createTeacher(teacher);
        if (createdTeacher != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") UUID id, @RequestBody @Valid TeacherDTO teacher) {
        TeacherDTO updatedTeacher = service.updateTeacher(id, teacher);
        if (updatedTeacher != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedTeacher);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") UUID id) {
        service.deleteTeacher(id);
    }
}
