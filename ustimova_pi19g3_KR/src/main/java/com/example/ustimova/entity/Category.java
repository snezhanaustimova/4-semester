// В БД ПУТЬ БУДЕТ "/categorys"!!! Это вышло случайно...

package com.example.ustimova.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfCreate;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfUpdate;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateOfCreate() {
        return dateOfCreate;
    }

    public LocalDate getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfCreate(LocalDate dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public void setDateOfUpdate(LocalDate dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfCreate=" + dateOfCreate +
                ", dateOfUpdate=" + dateOfUpdate +
                '}';
    }

    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    private List<Task> tasks;
}
