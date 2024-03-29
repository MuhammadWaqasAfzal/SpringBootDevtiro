package com.waqas.learning.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsById(List<Long> ids) {
        return studentRepository.findAllById(ids);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public String deleteStudent(List<Long> ids) {

        String message = "";
        for (int i = 0; i < ids.size(); i++) {
            Optional<Student> student = studentRepository.findById(ids.get(i));
            if (student.isPresent()) {
                studentRepository.deleteById(ids.get(i));
            } else {
                message = message + ids.get(i).toString() + ", ";
            }
        }

        if (message.equals(""))
            return "All students deleted successfully";
        else
            return "All students deleted except student with Ids : " + message;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }




    public Optional<Student> getStudentByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        System.out.println(student.toString());
        return student;
    }

    public String updateStudent(Student s) {
        if (s.getId().toString().isEmpty()) {
            return "Student id is required";
        } else {
            Optional<Student> studentOptional = studentRepository.findById(s.getId());
            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();
                if (s.getName()!=null && !s.getName().isEmpty())
                    student.setName(s.getName());
                if (s.getEmail()!=null && !s.getEmail().isEmpty())
                    student.setEmail(s.getEmail());
                if (s.getDob()!=null && !s.getDob().toString().isEmpty())
                    student.setDob(s.getDob());
                student.setId(s.getId());

                studentRepository.save(student);
                return "Student updated successfully";
            } else {
                return "Student does not exist";
            }
        }
    }
}
