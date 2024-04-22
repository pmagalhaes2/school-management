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
import tech.ada.school.management.domain.dto.v1.StudentDTO;
import tech.ada.school.management.domain.exceptions.NotFoundException;
import tech.ada.school.management.service.student.IStudentService;

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

    @Operation(summary = "Get all students", description = "Retrieves a list of all students.", tags = {"Student"})
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Get student by ID", description = "Retrieves a student by their ID.", tags = {"Student"})
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable("id") UUID id) throws NotFoundException {
        StudentDTO foundedStudent = service.getById(id);
        return ResponseEntity.ok().body(foundedStudent);
    }

    @Operation(summary = "Create a new student", description = "Creates a new student.", tags = {"Student"})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = StudentDTO.class,
                            requiredProperties = {"name"}
                    ),
                    examples = @ExampleObject(
                            value = "{\"name\":\"John Doe\"}"
                    )
            )
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO student) {
        StudentDTO createdStudent = service.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @Operation(summary = "Update a student", description = "Updates an existing student.", tags = {"Student"})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = StudentDTO.class,
                            requiredProperties = {"name"}
                    ),
                    examples = @ExampleObject(
                            value = "{\"name\":\"John Doe\"}"
                    )
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") UUID id, @RequestBody @Valid StudentDTO student) throws NotFoundException {
        StudentDTO updatedStudent = service.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @Operation(summary = "Delete a student", description = "Deletes an existing student.", tags = {"Student"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteStudent(@PathVariable("id") UUID id) throws NotFoundException {
        service.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
