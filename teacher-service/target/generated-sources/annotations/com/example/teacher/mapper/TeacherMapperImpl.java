package com.example.teacher.mapper;

import com.example.teacher.dto.TeacherDto;
import com.example.teacher.model.Teacher;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-16T21:57:10+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public TeacherDto entityToDto(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setId( teacher.getId() );
        teacherDto.setName( teacher.getName() );
        teacherDto.setAge( teacher.getAge() );
        teacherDto.setSubject( teacher.getSubject() );
        teacherDto.setDateOfJoining( teacher.getDateOfJoining() );

        return teacherDto;
    }
}
