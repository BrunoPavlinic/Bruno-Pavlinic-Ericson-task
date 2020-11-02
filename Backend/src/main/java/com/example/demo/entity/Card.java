package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue
    private int id;

    private int list;
    private String text;

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
}
