package com.programming.examproject.repo;

import com.programming.examproject.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepo extends JpaRepository<Supervisor, Long>
{
}
