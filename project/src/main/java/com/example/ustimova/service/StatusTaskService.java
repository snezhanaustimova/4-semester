package com.example.ustimova.service;

import com.example.ustimova.entity.StatusTask;
import com.example.ustimova.repository.StatusTaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusTaskService {

    @Autowired
    private StatusTaskRepository statusTaskRepository;

    public List<StatusTask> findAll() {
        return statusTaskRepository.findAll();
    }

    public StatusTask create(StatusTask statusTask) {
        return statusTaskRepository.save(statusTask);
    }

    public StatusTask update(StatusTask statusTask, StatusTask statusTaskFromDB) {
        BeanUtils.copyProperties(statusTask, statusTaskFromDB, "id");
        return statusTaskRepository.save(statusTaskFromDB);
    }

    public boolean delete (StatusTask statusTask) {
        if (statusTask != null) {
            statusTaskRepository.delete(statusTask);
            return true;
        }
        return false;
    }
}