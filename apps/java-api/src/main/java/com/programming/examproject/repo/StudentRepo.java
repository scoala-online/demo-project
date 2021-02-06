package com.programming.examproject.repo;

import com.programming.examproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long>
{
}
