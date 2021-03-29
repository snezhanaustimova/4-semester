package com.example.ustimova.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfCreate;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfUpdate;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfDone;

    @Column
    private boolean done;

    public Long getId() {
        return id;
    }

    public LocalDate getDateOfCreate() {
        return dateOfCreate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateOfUpdate() {
        return dateOfUpdate;
    }

    public LocalDate getDateOfDone() {
        return dateOfDone;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private List<Category> categories;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfCreate(LocalDate dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public void setDateOfUpdate(LocalDate dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfDone(LocalDate dateOfDone) {
        this.dateOfDone = dateOfDone;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfCreate=" + dateOfCreate +
                ", dateOfUpdate=" + dateOfUpdate +
                ", dateOfDone=" + dateOfDone +
                ", done=" + done +
                ", user=" + user +
                ", categories=" + categories +
                '}';
    }
}
