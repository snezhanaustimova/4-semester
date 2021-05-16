package com.example.ustimova.controller;

import com.example.ustimova.entity.Role;
import com.example.ustimova.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> readAll(){
        final List<Role> rolesList = roleService.findAll();
        return rolesList != null && !rolesList.isEmpty()
                ? new ResponseEntity<>(rolesList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Role> getOne(@PathVariable(name = "id") Role role) {
        final Role currentRole = role;
        return role != null
                ? new ResponseEntity<>(currentRole, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody Role role){
        Role newRole = roleService.create(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") Role roleFromDB,
                                 @RequestBody Role role) {
        Role updatedRole = roleService.update(role, roleFromDB);
        if (updatedRole != null) {
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Role>> delete(@PathVariable(name = "id") Role role) {
        if (roleService.delete(role)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}