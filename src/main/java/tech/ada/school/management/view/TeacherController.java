package tech.ada.school.management.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.school.management.domain.exceptions.NotFoundException;
import tech.ada.school.management.domain.dto.v1.TeacherDTO;
import tech.ada.school.management.domain.service.teacher.ITeacherService;

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
    public ResponseEntity<List<TeacherDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getById(@PathVariable("id") UUID id) throws NotFoundException {
        TeacherDTO foundedTeacher = service.getById(id);
        return ResponseEntity.ok().body(foundedTeacher);
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody @Valid TeacherDTO teacher) {
        TeacherDTO createdTeacher = service.createTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") UUID id, @RequestBody @Valid TeacherDTO teacher) throws NotFoundException {
        TeacherDTO updatedTeacher = service.updateTeacher(id, teacher);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable("id") UUID id) throws NotFoundException {
        service.deleteTeacher(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
