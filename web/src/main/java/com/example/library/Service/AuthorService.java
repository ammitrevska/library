package com.example.library.Service;

import com.example.library.Model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAll();

    Optional<Author> findById(Long id);
}
