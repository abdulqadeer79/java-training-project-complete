package com.example.student.mapper;

import com.example.student.dto.StudentDto;
import com.example.student.model.Student;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto entityToDto(Student student);
    Student DtoToEntity(StudentDto studentDto);
    List<StudentDto> entityToDto(List<Student> studentList);
}
