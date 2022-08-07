package com.example.student.controller;

import com.example.student.exception.StudentNotFoundException;
import com.example.student.dto.StudentDto;
import com.example.student.dto.TeacherDto;
import com.example.student.feign.TeacherFeignService;
import com.example.student.response_object.StudentTeacherResponseVO;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherFeignService teacherFeignService;

    // POST methods
    @PostMapping
    public ResponseEntity<String> createStudent(@Valid @RequestBody StudentDto studentDto) {
        boolean isComplete = studentService.createAndUpdateStudent(0, studentDto);
        ResponseEntity<String> responseEntity;
        if (isComplete) {
            responseEntity = status(HttpStatus.OK).body("Student created successfully.");
            return responseEntity;
        }
        responseEntity = status(HttpStatus.BAD_REQUEST).body("Failed to create Student.");
        return responseEntity;
    }

    // GET methods
    @GetMapping("/{id}")
    public ResponseEntity findStudentById(@PathVariable int id) throws StudentNotFoundException {
//        try {
            Optional<StudentDto> myStudent = studentService.findStudentById(id);
            return new ResponseEntity<Optional<StudentDto>>(myStudent, HttpStatus.FOUND);
//        }
//        catch (ResourceNotFoundCustomException e) {
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> findAllStudents() {
        List<StudentDto> studentList = studentService.findAllStudents();
        return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<StudentDto>> findStudentByAge(@PathVariable int age) {
        List<StudentDto> studentList = studentService.findStudentByAge(age);
        return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.FOUND);
    }

    @GetMapping("/hobby/{hobby}")
    public ResponseEntity<List<StudentDto>> findStudentByHobby(@PathVariable String hobby) {
        List<StudentDto> studentList = studentService.findStudentByHobby(hobby);
        return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.FOUND);
    }

    @GetMapping("/teacher/name/{teacherName}")
    public ResponseEntity findStudentByTeacherName(@PathVariable String teacherName) {
        List<StudentDto> studentList = studentService.findTeacherByName(teacherName);
        return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.FOUND);
    }

    @GetMapping("/teacher/age/{age}")
    public ResponseEntity<StudentTeacherResponseVO> findStudentTeacherByAge(@PathVariable int age) {
        List<TeacherDto> teacherList = studentService.findTeacherByAge(age);
        List<StudentDto> studentList = studentService.findStudentByAge(age);
        StudentTeacherResponseVO studentTeacherResponseVO = new StudentTeacherResponseVO();
        studentTeacherResponseVO.studentList = studentList;
        studentTeacherResponseVO.teacherList = teacherList;
        return new ResponseEntity<StudentTeacherResponseVO>(studentTeacherResponseVO, HttpStatus.OK);
    }

    @GetMapping(value = "/date/before")
    public ResponseEntity<List<StudentDto>> findStudentBeforeADateOfBirth(@DateTimeFormat(pattern = "dd:MM:yyyy") @RequestParam(value = "date") Date dateOfBirth) {
        List<StudentDto> studentList = studentService.findStudentBeforeADateOfBirth(dateOfBirth);
        return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/date/after")
    public ResponseEntity<List<StudentDto>> findStudentAfterADateOfBirth(@DateTimeFormat(pattern = "dd:MM:yyyy") @RequestParam(value = "date") Date dateOfBirth) {
        List<StudentDto> studentList = studentService.findStudentAfterADateOfBirth(dateOfBirth);
        return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.FOUND);
    }

    // PUT methods
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody StudentDto studentDto) {
        boolean isComplete = studentService.createAndUpdateStudent(id, studentDto);
        ResponseEntity<String> responseEntity;
        if (isComplete) {
            responseEntity = status(HttpStatus.OK).body("Student updated successfully.");
            return responseEntity;
        }
        responseEntity = status(HttpStatus.BAD_REQUEST).body("Failed to update Student.");
        return responseEntity;
    }

    // DELETE methods
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<String>("student deleted", HttpStatus.OK);
    }
}



