package com.example.library.Service;

import com.example.library.Model.Author;
import com.example.library.Model.Book;
import com.example.library.Model.Enums.Category;
import com.example.library.Model.dto.BookDto;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> ListAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Long author, int availableCopies);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, String name, Category category, Long author, int availableCopies);
    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> takenById(Long id);
}
