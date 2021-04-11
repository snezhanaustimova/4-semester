package com.example.ustimova.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="S_TASK")
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = true)
    private User developer;

    @ManyToOne
    @JoinColumn(name = "analyst_id", nullable = true)
    private User analyst;

    @ManyToOne
    @JoinColumn(name = "tester_id", nullable = true)
    private User tester;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusTask statusTask;

}
