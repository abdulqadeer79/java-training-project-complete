package com.example.teacher.mapper;

import com.example.teacher.dto.TeacherDto;
import com.example.teacher.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDto entityToDto(Teacher teacher);
}
