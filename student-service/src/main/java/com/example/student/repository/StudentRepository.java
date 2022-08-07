package com.example.student.repository;

import com.example.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public List<Student> findStudentByAge(int age);
    public List<Student> findStudentByHobby(String hobby);
    public List<Student> findByTeacherId(int teacherId);

}

