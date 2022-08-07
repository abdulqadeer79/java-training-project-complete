package com.example.teacher.service;

import com.example.teacher.dto.ErrorDto;
import com.example.teacher.dto.TeacherDto;
import com.example.teacher.mapper.TeacherMapper;
import com.example.teacher.model.Teacher;
import com.example.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper mapper;

    public Teacher createAndUpdateTeacher(Teacher teacher) {
        teacher.setActive(true);
        return teacherRepository.save(teacher);
    }

    public List<Teacher> findAllTeachers() {

        return teacherRepository.findAll()
                .stream()
                .filter(Teacher::isActive)
                .collect(Collectors.toList());
    }

    public Optional<Teacher> findTeacherById(int id) {
        if (teacherRepository.findTeacherById(id).isActive()){
            return teacherRepository.findById(id);
        }
        return Optional.empty();
    }

    public TeacherDto findTeacherByName(String name) {
        Teacher teacher =  teacherRepository.findTeacherByName(name);
        if (teacher == null) {
            ErrorDto errorDto = new ErrorDto("custom error from teacher service", "message from teacher service");
            List<ErrorDto> errorDtoList = new ArrayList<>();
            errorDtoList.add(errorDto);
            TeacherDto teacherDto = new TeacherDto(0, null, 0, null, null, errorDtoList);
            return teacherDto;
        }
        else {
            TeacherDto teacherDto = mapper.entityToDto(teacher);
            return teacherDto;
        }
    }

    public boolean deleteTeacherById(int id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        boolean isDeleted = false;
        if ((teacher != null) && (teacher.isActive())) {
            isDeleted = true;
            teacher.setActive(false);
            teacherRepository.save(teacher);
        }
        return isDeleted;
    }

    public List<Teacher> findTeacherByAgeAndSubject(int age, String subject) {
        return teacherRepository.findTeacherByAgeAndSubject(age, subject);
    }

    public List<Teacher> findTeacherByAge(int age) {
        return teacherRepository.findTeacherByAge(age);
    }
}
