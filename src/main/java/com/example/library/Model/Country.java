package com.example.library.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String Name;
    private String Continent;

    public Country() {
    }

    public Country(Long id, String name, String continent) {
        Id = id;
        Name = name;
        Continent = continent;
    }
}


