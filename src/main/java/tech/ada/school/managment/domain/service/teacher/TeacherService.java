package tech.ada.school.managment.domain.service.teacher;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.school.managment.domain.exceptions.NotFoundException;
import tech.ada.school.managment.domain.dto.v1.TeacherDTO;
import tech.ada.school.managment.domain.entities.Teacher;
import tech.ada.school.managment.domain.mappers.TeacherMapper;
import tech.ada.school.managment.repositories.TeacherRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private TeacherRepository repository;

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher newTeacher = TeacherMapper.toEntity(teacherDTO);
        repository.save(newTeacher);
        return TeacherMapper.toDto(newTeacher);
    }

    @Override
    public List<TeacherDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(TeacherMapper::toDto)
                .toList();
    }

    @Override
    public TeacherDTO getById(UUID id) throws NotFoundException {
        return TeacherMapper.toDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(TeacherDTO.class, String.valueOf(id))));
    }

    @Override
    @Transactional
    public TeacherDTO updateTeacher(UUID id, TeacherDTO teacherDTO) throws NotFoundException {
        TeacherDTO foundedTeacher = getById(id);
        foundedTeacher.setName(teacherDTO.getName());
        return TeacherMapper.toDto(repository.save(TeacherMapper.toEntity(foundedTeacher)));
    }

    @Override
    @Transactional
    public void deleteTeacher(UUID id) throws NotFoundException {
        TeacherDTO foundedTeacher = getById(id);
        repository.delete(TeacherMapper.toEntity(foundedTeacher));
    }
}
