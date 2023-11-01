package com.example.CRUD.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "attempt")
    private Users users;

    private int attempts;

    public Attempt() { attempts = 0; }

}
