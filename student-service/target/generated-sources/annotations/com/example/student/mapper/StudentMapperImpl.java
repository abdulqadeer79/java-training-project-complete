package com.example.student.mapper;

import com.example.student.dto.StudentDto;
import com.example.student.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-07T16:54:30+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDto entityToDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setId( student.getId() );
        studentDto.setName( student.getName() );
        studentDto.setAge( student.getAge() );
        studentDto.setRollNo( student.getRollNo() );
        studentDto.setHobby( student.getHobby() );
        studentDto.setDateOfBirth( student.getDateOfBirth() );
        studentDto.setTeacherId( student.getTeacherId() );

        return studentDto;
    }

    @Override
    public Student DtoToEntity(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( studentDto.getId() );
        student.setName( studentDto.getName() );
        student.setAge( studentDto.getAge() );
        student.setRollNo( studentDto.getRollNo() );
        student.setHobby( studentDto.getHobby() );
        student.setDateOfBirth( studentDto.getDateOfBirth() );
        student.setTeacherId( studentDto.getTeacherId() );

        return student;
    }

    @Override
    public List<StudentDto> entityToDto(List<Student> studentList) {
        if ( studentList == null ) {
            return null;
        }

        List<StudentDto> list = new ArrayList<StudentDto>( studentList.size() );
        for ( Student student : studentList ) {
            list.add( entityToDto( student ) );
        }

        return list;
    }
}
