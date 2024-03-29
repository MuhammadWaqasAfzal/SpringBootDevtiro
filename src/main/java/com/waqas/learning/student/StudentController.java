package com.waqas.learning.student;

import com.waqas.learning.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "get_all_students")
    public ResponseEntity<ApiResponse<?>> getAllStudents(){
       return ResponseEntity.ok(new ApiResponse<>(200, "All Students",  studentService.getAllStudents()));
    }

    @PostMapping(path = "get_students")
    public  ResponseEntity<ApiResponse<?>> getStudents(@RequestParam("id") List<Long> ids){
        return ResponseEntity.ok(new ApiResponse<>(200, "All Students",  studentService.getStudentsById(ids)));
    }

    @PostMapping(path = "delete_student")
    public ResponseEntity<ApiResponse<?>> deleteStudent(@RequestParam("id") List<Long> ids){
       String message =  studentService.deleteStudent(ids);
        ApiResponse<Object> response = new ApiResponse<>(200, message);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "add_student")
    public ResponseEntity<ApiResponse<?>> addStudent(@RequestBody Student student){
       Student saveStudent =  studentService.addStudent(student);
        ApiResponse<Object> response = new ApiResponse<>(200, "Student  has been added successfully.",saveStudent);
        return ResponseEntity.ok(response);
    }

//    @PostMapping(path = "update_student")
//    public ResponseEntity<ApiResponse<?>> updateStudent(@RequestBody Student student){
//        Student saveStudent =  studentService.addStudent(student);
//        if(saveStudent!=null){
//            ApiResponse<Object> response = new ApiResponse<>(200, "Student  has been updated successfully.",saveStudent);
//            return ResponseEntity.ok(response);
//
//        }else{
//            ApiResponse<Object> response = new ApiResponse<>(404, "Student  has not been updated .",saveStudent);
//            return ResponseEntity.status(404).body(response);
//        }
//    }


    @PostMapping(path = "get_student_by_email")
    public ResponseEntity<ApiResponse<?>> getStudentByEmail(@RequestBody Student student){
        Optional<Student> saveStudent =  studentService.getStudentByEmail(student.getEmail());
        ApiResponse<Object> response = new ApiResponse<>(200, "Success.",saveStudent);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "update_student")
    public ResponseEntity<ApiResponse<?>> updateStudent(@RequestBody Student student){
        String message =  studentService.updateStudent(student);
        ApiResponse<Object> response = new ApiResponse<>(200, message,studentService.getStudentById(student.getId()));
        return ResponseEntity.ok(response);
    }

}
