package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class BoardList {
    @Id
    @GeneratedValue
    private int id;

    private int board;

    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
