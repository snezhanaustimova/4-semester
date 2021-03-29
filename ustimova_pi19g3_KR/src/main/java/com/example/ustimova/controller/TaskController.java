package com.example.ustimova.controller;

import com.example.ustimova.entity.Task;
import com.example.ustimova.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> readAll(){
        final List<Task> tasksList = taskService.findAll();
        return tasksList != null && !tasksList.isEmpty()
                ? new ResponseEntity<>(tasksList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getOne(@PathVariable(name = "id") Task task) {
        final Task currentTask = task;
        return task != null
                ? new ResponseEntity<>(currentTask, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody Task task){
        Task newTask = taskService.create(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") Task taskFromDB,
                                 @RequestBody Task task) {
        Task updatedTask = taskService.update(task, taskFromDB);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Task>> delete(@PathVariable(name = "id") Task task) {
        if (taskService.delete(task)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
