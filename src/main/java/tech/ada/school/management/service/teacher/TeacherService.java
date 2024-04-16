package tech.ada.school.management.service.teacher;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.school.management.domain.dto.v1.TeacherDTO;
import tech.ada.school.management.domain.entities.Teacher;
import tech.ada.school.management.domain.exceptions.NotFoundException;
import tech.ada.school.management.domain.mappers.TeacherMapper;
import tech.ada.school.management.external.FeignBoredApi;
import tech.ada.school.management.repositories.TeacherRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private TeacherRepository repository;

    @Autowired
    private FeignBoredApi boredApi;

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        String activity = boredApi != null ? boredApi.getActivity().activity() : null;
        Teacher newTeacher = TeacherMapper.toEntity(teacherDTO, activity);
        return TeacherMapper.toDto(repository.save(newTeacher));
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
        foundedTeacher.setCpf(teacherDTO.getCpf());
        return TeacherMapper.toDto(repository.save(TeacherMapper.toEntity(foundedTeacher,
                foundedTeacher.getActivity())));
    }

    @Override
    @Transactional
    public void deleteTeacher(UUID id) throws NotFoundException {
        TeacherDTO foundedTeacher = getById(id);
        repository.delete(TeacherMapper.toEntity(foundedTeacher, foundedTeacher.getActivity()));
    }
}
