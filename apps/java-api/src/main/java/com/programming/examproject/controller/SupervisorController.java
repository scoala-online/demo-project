package com.programming.examproject.controller;

import com.programming.examproject.exception.ResourceNotFoundException;
import com.programming.examproject.model.Supervisor;
import com.programming.examproject.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController
{
    @Autowired
    SupervisorService supervisorService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Supervisor>> getAllSupervisors () {
        List<Supervisor> supervisorList = supervisorService.getAllSupervisors();
        return new ResponseEntity<>(supervisorList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Supervisor> getSupervisorById (@PathVariable("id") long id){
        Supervisor supervisor = supervisorService.getSupervisorById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No Supervisor found with this ID", new ResourceNotFoundException()
                ));
        return new ResponseEntity<>(supervisor, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Supervisor> addSupervisor (@RequestBody Supervisor supervisor) {
        Supervisor savedSupervisor = supervisorService.addSupervisor(supervisor);
        return new ResponseEntity<>(savedSupervisor, HttpStatus.CREATED);
    }

    @PutMapping(value = ("/{id}"))
    public ResponseEntity<Supervisor> updateSupervisor (@PathVariable("id") long id,
                                                        @RequestBody Supervisor supervisor) {
        if (supervisorService.supervisorExists(id)) {
            Supervisor updatedSupervisor = supervisorService.updateSupervisor(id, supervisor);
            return new ResponseEntity<>(updatedSupervisor, HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cannot update non-existing Supervisor", new ResourceNotFoundException()
            );
        }
    }

    @DeleteMapping(value = ("/{id}"))
    public ResponseEntity<HttpStatus> deleteSupervisor (@PathVariable("id") long id) {
        if (supervisorService.supervisorExists(id)) {
            supervisorService.deleteSupervisor(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cannot delete non-existing Supervisor", new ResourceNotFoundException()
            );
        }
    }
}
