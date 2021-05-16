package com.example.ustimova.repository;

import com.example.ustimova.entity.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTaskRepository extends JpaRepository <StatusTask, Long> {
    StatusTask findById (long id);
}