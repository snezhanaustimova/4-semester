package com.example.ustimova.service;

import com.example.ustimova.entity.User;
import com.example.ustimova.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User create(User user) {
        return userRepo.save(user);
    }

    public User update(User user, User userFromDB) {
        BeanUtils.copyProperties(user, userFromDB, "id");
        return userRepo.save(userFromDB);
    }

    public boolean delete (User user) {
        if (user != null) {
            userRepo.delete(user);
            return true;
        }
        return false;
    }
}