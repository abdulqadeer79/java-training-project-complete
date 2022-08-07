package com.example.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.student.constant.Constant.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = ID_NOT_NULL_ERROR_MESSAGE)
    private int id;
    @NotEmpty(message = NAME_NOT_EMPTY_ERROR_MESSAGE)
    private String name;
    @Max(value = 65, message = MIN_MAX_AGE_ERROR_MESSAGE)
    @Min(value = 18, message = MIN_MAX_AGE_ERROR_MESSAGE)
    private int age;
    @Column(unique = true, name = "rollNo")
    private int rollNo;
    @Size(min = 4, max = 50)
    private String hobby;
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd:MM:yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Positive
    private int teacherId;
    private List<ErrorDto> errors = new ArrayList<>();
}
