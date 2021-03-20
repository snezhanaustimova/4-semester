package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data // создает геттеры и сеттеры
@NoArgsConstructor // конструктор бзе аргум
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String postalCode;
    private LocalDate birthday;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postalCode=" + postalCode + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
}
