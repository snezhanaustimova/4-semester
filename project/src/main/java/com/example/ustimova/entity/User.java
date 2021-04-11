package com.example.ustimova.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="S_USERS")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String middleName;
    @Column(nullable = false)
    private String passwordHash;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "developer")
    private Set<Task> developerTasks;

    @OneToMany(mappedBy = "analyst")
    private Set<Task> analystTasks;

    @OneToMany(mappedBy = "tester")
    private Set<Task> testerTasks;

}
