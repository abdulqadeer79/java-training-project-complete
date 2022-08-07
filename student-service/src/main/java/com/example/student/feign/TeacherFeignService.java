package com.example.student.feign;

import com.example.student.dto.TeacherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static com.example.student.constant.Constant.TEACHER_BASE_URI;

@FeignClient(value = "teacher-api", url = TEACHER_BASE_URI, fallback = TeacherFeignFallBack.class)
public interface TeacherFeignService {

    @GetMapping("/name/{teacherName}")
    ResponseEntity<TeacherDto> findTeacherByName(@PathVariable String teacherName);

    @GetMapping("/age/{age}")
    ResponseEntity<List<TeacherDto>> findTeacherByAge(@PathVariable int age);
}
