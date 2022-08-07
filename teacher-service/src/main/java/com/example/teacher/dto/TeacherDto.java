package com.example.teacher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private int id;
    private String name;
    private int age;
    private String subject;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd:MM:yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;
    private List<ErrorDto> errors = new ArrayList<>();
}
