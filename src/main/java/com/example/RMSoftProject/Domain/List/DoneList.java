package com.example.RMSoftProject.Domain.List;

import com.example.RMSoftProject.Domain.Calendar.Calendar;
import com.example.RMSoftProject.Domain.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DoneList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "done_id")
    private Long id;

    private String title;

    private String Date;

    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;


}
