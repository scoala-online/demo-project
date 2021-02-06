package com.programming.examproject.service;

import com.programming.examproject.model.Student;
import com.programming.examproject.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    @Autowired
    StudentRepo studentRepo;

    public List<Student> getAllStudents () {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentById (long id) {
        return studentRepo.findById(id);
    }

    public Student addStudent (Student student) {
        Student studentToSave = new Student();
        studentToSave.setFirstName(student.getFirstName());
        studentToSave.setLastName(student.getLastName());
        studentToSave.setEmail(student.getEmail());
        studentToSave.setSupervisor(student.getSupervisor());

        return studentRepo.save(student);
    }

    public Student updateStudent (long id, Student student) {
        Student studentToUpdate = studentRepo.findById(id).get();
        if (student.getFirstName() != null) {
            studentToUpdate.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            studentToUpdate.setLastName(student.getLastName());
        }
        if (student.getEmail() != null) {
            studentToUpdate.setEmail(student.getEmail());
        }
        studentToUpdate.setSupervisor(student.getSupervisor());
        return studentRepo.save(studentToUpdate);
    }

    public void deleteStudent (long id) {
        studentRepo.deleteById(id);
    }

    public boolean studentExists (long id) {
        return studentRepo.existsById(id);
    }
}
