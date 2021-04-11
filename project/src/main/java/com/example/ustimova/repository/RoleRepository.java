package com.example.ustimova.repository;

import com.example.ustimova.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findById (long id);
}