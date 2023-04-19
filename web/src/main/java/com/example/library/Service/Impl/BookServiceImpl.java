package com.example.library.Service.Impl;

import com.example.library.Model.Author;
import com.example.library.Model.Book;
import com.example.library.Model.Enums.Category;
import com.example.library.Model.Exceptions.AuthorNotFoundException;
import com.example.library.Model.Exceptions.BookNotFoundException;
import com.example.library.Model.dto.BookDto;
import com.example.library.Repository.AuthorRepository;
import com.example.library.Repository.BookRepository;
import com.example.library.Service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> ListAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Category category, Long authorId, int availableCopies) {

        Author author = this.authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(authorId)
        );

        this.bookRepository.deleteByName(name);
        return Optional.of(this.bookRepository.save(new Book(name,category, author, availableCopies)));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(
                () -> new AuthorNotFoundException(bookDto.getAuthor())
        );

        this.bookRepository.deleteByName(bookDto.getName());
        return Optional.of(this.bookRepository.save(
                new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {

        Book book = this.bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );

        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(
                () -> new AuthorNotFoundException(bookDto.getAuthor())
        );

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, int availableCopies) {

        Book book = this.bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );

        Author author = this.authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(authorId)
        );

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));

    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> takenById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );
        book.setAvailableCopies(book.getAvailableCopies()-1);

        return Optional.of(this.bookRepository.save(book));
    }
}
