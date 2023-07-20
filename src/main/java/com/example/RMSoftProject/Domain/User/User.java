package com.example.RMSoftProject.Domain.User;


import com.example.RMSoftProject.Domain.Calendar.Calendar;
import com.example.RMSoftProject.Domain.List.TodoList;
import com.example.RMSoftProject.Domain.Squid.Squid;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String squidname;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Squid squid;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoList> todos;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Calendar calendar;

}
