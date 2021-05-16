package com.example.ustimova.service;

import com.example.ustimova.entity.Status;
import com.example.ustimova.repository.StatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Status create(Status status) {
        return statusRepository.save(status);
    }

    public Status update(Status status, Status statusFromDB) {
        BeanUtils.copyProperties(status, statusFromDB, "id");
        return statusRepository.save(statusFromDB);
    }

    public boolean delete (Status status) {
        if (status != null) {
            statusRepository.delete(status);
            return true;
        }
        return false;
    }
}