package com.example.library.Repository;

import com.example.library.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
