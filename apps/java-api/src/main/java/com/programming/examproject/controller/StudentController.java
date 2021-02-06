package com.programming.examproject.controller;

import com.programming.examproject.exception.ResourceNotFoundException;
import com.programming.examproject.model.Student;
import com.programming.examproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    StudentService studentService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Student>> getAllStudents () {
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudentById (@PathVariable("id") long id){
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No Student found with this ID", new ResourceNotFoundException()
                ));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Student> addStudent (@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping(value = ("/{id}"))
    public ResponseEntity<Student> updateStudent (@PathVariable("id") long id,
                                                  @RequestBody Student student) {
        if (studentService.studentExists(id)) {
            Student updatedStudent = studentService.updateStudent(id, student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cannot update non-existing Student", new ResourceNotFoundException()
            );
        }
    }

    @DeleteMapping(value = ("/{id}"))
    public ResponseEntity<HttpStatus> deleteStudent (@PathVariable("id") long id) {
        if (studentService.studentExists(id)) {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cannot delete non-existing Student", new ResourceNotFoundException()
            );
        }
    }
}
