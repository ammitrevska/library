package com.example.library.Model.dto;

import com.example.library.Model.Author;
import com.example.library.Model.Enums.Category;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class BookDto {

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long author;

    private int availableCopies;


    public BookDto() {
    }

    public BookDto(String name, Category category, Long author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
