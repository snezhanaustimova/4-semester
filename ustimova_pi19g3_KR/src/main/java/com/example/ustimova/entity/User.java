package com.example.ustimova.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String patronymic;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @Column
    private String login;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfCreate;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfUpdate;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getDateOfCreate() {
        return dateOfCreate;
    }

    public LocalDate getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfUpdate(LocalDate dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public void setDateOfCreate(LocalDate dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", login='" + login + '\'' +
                ", dateOfCreate=" + dateOfCreate +
                ", dateOfUpdate=" + dateOfUpdate +
                '}';
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Task> tasks;
}

