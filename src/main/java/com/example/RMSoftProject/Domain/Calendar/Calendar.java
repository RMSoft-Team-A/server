package com.example.RMSoftProject.Domain.Calendar;


import com.example.RMSoftProject.Domain.List.DoneList;
import com.example.RMSoftProject.Domain.List.TodoList;
import com.example.RMSoftProject.Domain.User.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<TodoList> todoList;
  @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<DoneList> doneList;

    private String date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
