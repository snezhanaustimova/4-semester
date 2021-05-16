package com.example.ustimova.controller;

import com.example.ustimova.entity.Status;
import com.example.ustimova.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuses")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<Status>> readAll(){
        final List<Status> statussList = statusService.findAll();
        return statussList != null && !statussList.isEmpty()
                ? new ResponseEntity<>(statussList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Status> getOne(@PathVariable(name = "id") Status status) {
        final Status currentStatus = status;
        return status != null
                ? new ResponseEntity<>(currentStatus, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody Status status){
        Status newStatus = statusService.create(status);
        return new ResponseEntity<>(newStatus, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") Status statusFromDB,
                                 @RequestBody Status status) {
        Status updatedStatus = statusService.update(status, statusFromDB);
        if (updatedStatus != null) {
            return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Status>> delete(@PathVariable(name = "id") Status status) {
        if (statusService.delete(status)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}