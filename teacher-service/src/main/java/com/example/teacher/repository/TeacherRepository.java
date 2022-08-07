package com.example.teacher.repository;

import com.example.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findTeacherById(int id);
    Teacher findTeacherByName( String name);
    List<Teacher> findTeacherByAgeAndSubject(int age, String subject);
    List<Teacher> findTeacherByAge(int age);

}
