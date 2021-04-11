package com.example.ustimova.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="S_STATUS")
@NoArgsConstructor
@Data
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "status")
    private Set<StatusTask> statusTaskList;
}
