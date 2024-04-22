package tech.ada.school.management.view;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.management.domain.dto.v1.TeacherDTO;
import tech.ada.school.management.domain.exceptions.NotFoundException;
import tech.ada.school.management.service.teacher.ITeacherService;

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

    @Operation(summary = "Get all teachers", description = "Retrieves a list of all teachers.", tags = {"Teacher"})
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Get teacher by ID", description = "Retrieves a teacher by their ID.", tags = {"Teacher"})
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getById(@PathVariable("id") UUID id) throws NotFoundException {
        TeacherDTO foundedTeacher = service.getById(id);
        return ResponseEntity.ok().body(foundedTeacher);
    }

    @Operation(summary = "Create a new teacher", description = "Creates a new teacher.", tags = {"Teacher"})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TeacherDTO.class,
                            requiredProperties = {"name"}
                    ),
                    examples = @ExampleObject(
                            value = "{\"name\":\"John Doe\"}"
                    )
            )
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody @Valid TeacherDTO teacher) {
        TeacherDTO createdTeacher = service.createTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    @Operation(summary = "Update a teacher", description = "Updates an existing teacher.", tags = {"Teacher"})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TeacherDTO.class,
                            requiredProperties = {"name"}
                    ),
                    examples = @ExampleObject(
                            value = "{\"name\":\"John Doe\"}"
                    )
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") UUID id, @RequestBody @Valid TeacherDTO teacher) throws NotFoundException {
        TeacherDTO updatedTeacher = service.updateTeacher(id, teacher);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacher);
    }

    @Operation(summary = "Delete a teacher", description = "Deletes an existing teacher.", tags = {"Teacher"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteTeacher(@PathVariable("id") UUID id) throws NotFoundException {
        service.deleteTeacher(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
