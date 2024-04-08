package tech.ada.school.managment.domain.service;

import org.springframework.stereotype.Service;
import tech.ada.school.managment.domain.dto.v1.TeacherDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService implements ITeacherService {
    private final List<TeacherDTO> teachers = new ArrayList<>();

    @Override
    public String createTeacher(String name) {
        teachers.add(new TeacherDTO(UUID.randomUUID(), name));
        return "Professor adicionado com sucesso!";
    }

    @Override
    public List<TeacherDTO> getAll() {
        return teachers;
    }

    @Override
    public TeacherDTO getById(UUID id) {
        Optional<TeacherDTO> foundedTeacher = teachers.stream().filter(t -> t.getId().equals(id)).findFirst();
        return foundedTeacher.orElse(null);
    }

    @Override
    public void updateTeacher(UUID id, String name) {
        Optional<TeacherDTO> foundedTeacher = teachers.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (foundedTeacher.isPresent()) {
            teachers.remove(foundedTeacher.get());
            teachers.add(new TeacherDTO(id, name));
        }
    }

    @Override
    public void deleteTeacher(UUID id) {
        Optional<TeacherDTO> foundedTeacher = teachers.stream().filter(t -> t.getId().equals(id)).findFirst();
        foundedTeacher.ifPresent(teachers::remove);
    }
}
