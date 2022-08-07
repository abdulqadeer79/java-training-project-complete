package com.example.teacher.controller;


import com.example.teacher.dto.TeacherDto;
import com.example.teacher.model.Teacher;
import com.example.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // POST methods
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Teacher teacher) {
        Teacher myStudent = teacherService.createAndUpdateTeacher(teacher);
        return new ResponseEntity<String>("Teacher created successfully", HttpStatus.CREATED);
    }

    // GET methods
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Teacher>> findStudentById(@PathVariable int id) {
        Optional<Teacher> myStudent = teacherService.findTeacherById(id);
        if (myStudent.isPresent()) {
            return new ResponseEntity<Optional<Teacher>>(myStudent, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> findAllStudents() {
        List<Teacher> studentList = teacherService.findAllTeachers();
        return new ResponseEntity<List<Teacher>>(studentList, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TeacherDto> findTeacherByName(@PathVariable String name) {
        TeacherDto myTeacher = teacherService.findTeacherByName(name);
        return new ResponseEntity<TeacherDto>(myTeacher, HttpStatus.OK);
    }


    @GetMapping("/age-subject/{age}/{subject}")
    public ResponseEntity<List<Teacher>> findTeacherByAgeAndSubject(@PathVariable int age, @PathVariable String subject) {
        List<Teacher> teacherList = teacherService.findTeacherByAgeAndSubject(age, subject);
        return new ResponseEntity<List<Teacher>>(teacherList, HttpStatus.FOUND);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Teacher>> findTeacherByAge(@PathVariable int age) {
        List<Teacher> teacherList = teacherService.findTeacherByAge(age);
        return new ResponseEntity<List<Teacher>>(teacherList, HttpStatus.OK);
    }

    // PUT methods
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateStudent(@PathVariable int id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        Teacher myTeacher = teacherService.createAndUpdateTeacher(teacher);
        return new ResponseEntity<Teacher>(myTeacher, HttpStatus.ACCEPTED);
    }

    // DELETE methods
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable int id) {
        boolean isDeleted = teacherService.deleteTeacherById(id);
        if (isDeleted) {
            return new ResponseEntity<String>("Teacher deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Teacher found", HttpStatus.NOT_FOUND);

    }

}
