package tech.ada.school.managment.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.managment.domain.dto.v1.TeacherDTO;
import tech.ada.school.managment.domain.service.ITeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public String createTeacher(@RequestBody TeacherDTO teacher) {
        return service.createTeacher(teacher.getName());
    }

    @PutMapping("/{id}")
    public void updateTeacher(@PathVariable("id") UUID id, @RequestBody TeacherDTO teacher) {
        service.updateTeacher(id, teacher.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") UUID id) {
        service.deleteTeacher(id);
    }
}
