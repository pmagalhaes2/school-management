package tech.ada.school.managment.domain.service.teacher;

import org.springframework.stereotype.Service;
import tech.ada.school.managment.domain.dto.exceptions.NotFoundException;
import tech.ada.school.managment.domain.dto.v1.TeacherDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService implements ITeacherService {
    private final List<TeacherDTO> teachers = new ArrayList<>();

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        TeacherDTO newTeacher = new TeacherDTO(UUID.randomUUID(), teacherDTO.getName());
        teachers.add(newTeacher);
        return newTeacher;
    }

    @Override
    public List<TeacherDTO> getAll() {
        return teachers;
    }

    @Override
    public TeacherDTO getById(UUID id) throws NotFoundException {
        Optional<TeacherDTO> foundedTeacher = teachers.stream().filter(t -> t.getId().equals(id)).findFirst();
        return foundedTeacher.orElseThrow(() -> new NotFoundException(TeacherDTO.class, String.valueOf(id)));
    }

    @Override
    public TeacherDTO updateTeacher(UUID id, TeacherDTO teacherDTO) throws NotFoundException {
        final TeacherDTO foundedTeacher = getById(id);
        teachers.remove(foundedTeacher);
        final TeacherDTO updatedTeacher = new TeacherDTO(id, teacherDTO.getName());
        teachers.add(updatedTeacher);
        return updatedTeacher;
    }

    @Override
    public void deleteTeacher(UUID id) throws NotFoundException {
        final TeacherDTO foundedTeacher = getById(id);
        teachers.remove(foundedTeacher);
    }
}
