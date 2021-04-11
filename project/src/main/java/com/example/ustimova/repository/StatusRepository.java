package com.example.ustimova.repository;

import com.example.ustimova.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository <Status, Long> {
    Status findById (long id);
}