package com.example.RMSoftProject.Domain.Squid;


import com.example.RMSoftProject.Domain.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Squid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "squid_id")

    private Long id;

    private String Level;

    private String exp;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
