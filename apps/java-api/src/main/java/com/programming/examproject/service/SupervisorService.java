package com.programming.examproject.service;

import com.programming.examproject.model.Supervisor;
import com.programming.examproject.repo.SupervisorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService
{
    @Autowired
    SupervisorRepo supervisorRepo;

    public List<Supervisor> getAllSupervisors () {
        return supervisorRepo.findAll();
    }

    public Optional<Supervisor> getSupervisorById (long id) {
        return supervisorRepo.findById(id);
    }

    public Supervisor addSupervisor (Supervisor supervisor) {
        Supervisor supervisorToSave = new Supervisor();
        supervisorToSave.setFirstName(supervisor.getFirstName());
        supervisorToSave.setLastName(supervisor.getLastName());

        return supervisorRepo.save(supervisorToSave);
    }

    public Supervisor updateSupervisor (long id, Supervisor supervisor) {
        Supervisor supervisorToUpdate = supervisorRepo.findById(id).get();
        supervisorToUpdate.setFirstName(supervisor.getFirstName());
        if (supervisor.getLastName() != null) {
            supervisorToUpdate.setLastName(supervisor.getLastName());
        }

        return supervisorRepo.save(supervisorToUpdate);
    }

    public void deleteSupervisor (long id) {
        supervisorRepo.deleteById(id);
    }

    public boolean supervisorExists (long id) {
       return supervisorRepo.existsById(id);
    }
}
