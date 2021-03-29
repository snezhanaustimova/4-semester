package com.example.ustimova.service;

import com.example.ustimova.entity.Task;
import com.example.ustimova.repository.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    public Task create(Task task) {
        return taskRepo.save(task);
    }

    public Task update(Task task, Task taskFromDB) {
        BeanUtils.copyProperties(task, taskFromDB, "id");
        return taskRepo.save(taskFromDB);
    }

    public boolean delete (Task task) {
        if (task != null) {
            taskRepo.delete(task);
            return true;
        }
        return false;
    }
}