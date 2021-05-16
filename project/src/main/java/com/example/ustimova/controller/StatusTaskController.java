package com.example.ustimova.controller;

import com.example.ustimova.entity.StatusTask;
import com.example.ustimova.service.StatusTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statusTasks")
public class StatusTaskController {
    private final StatusTaskService statusTaskService;

    @Autowired
    public StatusTaskController(StatusTaskService statusTaskService) {
        this.statusTaskService = statusTaskService;
    }

    @GetMapping
    public ResponseEntity<List<StatusTask>> readAll(){
        final List<StatusTask> statusTasksList = statusTaskService.findAll();
        return statusTasksList != null && !statusTasksList.isEmpty()
                ? new ResponseEntity<>(statusTasksList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StatusTask> getOne(@PathVariable(name = "id") StatusTask statusTask) {
        final StatusTask currentStatusTask = statusTask;
        return statusTask != null
                ? new ResponseEntity<>(currentStatusTask, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody StatusTask statusTask){
        StatusTask newStatusTask = statusTaskService.create(statusTask);
        return new ResponseEntity<>(newStatusTask, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") StatusTask statusTaskFromDB,
                                 @RequestBody StatusTask statusTask) {
        StatusTask updatedStatusTask = statusTaskService.update(statusTask, statusTaskFromDB);
        if (updatedStatusTask != null) {
            return new ResponseEntity<>(updatedStatusTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<StatusTask>> delete(@PathVariable(name = "id") StatusTask statusTask) {
        if (statusTaskService.delete(statusTask)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}