package com.example.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.student.constant.Constant.NAME_NOT_EMPTY_ERROR_MESSAGE;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
     private int id;
     @NotEmpty(message = NAME_NOT_EMPTY_ERROR_MESSAGE)
     private String name;
     @Max(65)
     @Min(24)
     private int age;
     @Size(min = 4, max = 20)
     private String subject;
     @NotNull
     @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd:MM:yyyy")
     @Temporal(TemporalType.DATE)
     private Date dateOfJoining;
     private List<ErrorDto> errors = new ArrayList<>();
}