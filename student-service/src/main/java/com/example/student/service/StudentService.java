package com.example.student.service;

import com.example.student.exception.StudentNotFoundException;
import com.example.student.dto.StudentDto;
import com.example.student.dto.TeacherDto;
import com.example.student.feign.TeacherFeignService;
import com.example.student.mapper.StudentMapper;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    @Autowired
    private TeacherFeignService teacherFeignService;

    public boolean createAndUpdateStudent(int id, StudentDto studentDto) {
        Optional<Student> student = repository.findById(id).filter(Student::isActive);
        if (student.isPresent() && studentDto != null) {
            Student updatedStudent = mapper.DtoToEntity(studentDto);
            updatedStudent.setId(id);
            updatedStudent.setActive(true);
            repository.save(updatedStudent);
            return true;
        }
        else if (!student.isPresent() && studentDto != null) {
            Student updatedStudent = mapper.DtoToEntity(studentDto);
            updatedStudent.setActive(true);
            repository.save(updatedStudent);
            return true;
        }
        return false;
    }

    public List<StudentDto> findAllStudents() {
        return repository.findAll()
                .stream()
                .filter(Student::isActive)
                .map(x -> mapper.entityToDto(x))
                .collect(Collectors.toList());
    }

    public Optional<StudentDto> findStudentById(int id) throws StudentNotFoundException {
        Optional<Student> student = repository.findById(id).filter(Student::isActive);
        if (student.isPresent()) {
            StudentDto studentDto = mapper.entityToDto(student.get());
            return Optional.of(studentDto);
        }
        else {
            throw new StudentNotFoundException("Student does not exists");
        }
    }

    public void deleteStudent(int id) {
        repository.deleteById(id);
    }

    public List<StudentDto> findStudentByAge(int age) {
        return repository.findStudentByAge(age)
                .stream()
                .filter(Student::isActive)
                .map(x -> mapper.entityToDto(x))
                .collect(Collectors.toList());
    }

    public List<StudentDto> findStudentByHobby(String hobby) {
        return repository.findStudentByHobby(hobby)
                .stream()
                .filter(Student::isActive)
                .map(x -> mapper.entityToDto(x))
                .collect(Collectors.toList());
    }

    public List<StudentDto> findStudentByTeacherId(int teacherId) {
        return repository.findByTeacherId(teacherId)
                .stream()
                .filter(Student::isActive)
                .map(x -> mapper.entityToDto(x))
                .collect(Collectors.toList());
    }

    public List<StudentDto> findStudentAfterADateOfBirth(Date dateOfBirth) {
        return repository.findAll()
                .stream()
                .filter(Student::isActive)
                .filter(x -> x.getDateOfBirth().after(dateOfBirth))
                .map(x -> mapper.entityToDto(x))
                .collect(Collectors.toList());
    }

    public List<StudentDto> findStudentBeforeADateOfBirth(Date dateOfBirth) {
        return repository.findAll()
                .stream()
                .filter(Student::isActive)
                .filter(x -> x.getDateOfBirth().before(dateOfBirth))
                .map(x -> mapper.entityToDto(x))
                .collect(Collectors.toList());
    }

    public List<StudentDto> findTeacherByName(String name) {
        TeacherDto teacherDto = teacherFeignService.findTeacherByName(name).getBody();
        if (teacherDto.getErrors().size() != 0) {
            StudentDto student = new StudentDto(0, null, 0, 0, null,null,0,teacherDto.getErrors());
            List<StudentDto> studentDtoList = new ArrayList<>();
            studentDtoList.add(student);
            return studentDtoList;
        }

        Integer teacherId = teacherDto.getId();
        List<StudentDto> studentList = this.findStudentByTeacherId(teacherId);
        return studentList;
    }

    public List<TeacherDto> findTeacherByAge(int age) {
        return teacherFeignService.findTeacherByAge(age).getBody();
    }
}
