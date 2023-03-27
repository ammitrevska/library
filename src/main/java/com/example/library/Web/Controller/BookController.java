package com.example.library.Web.Controller;

import com.example.library.Model.Author;
import com.example.library.Model.Book;
import com.example.library.Model.Enums.Category;
import com.example.library.Service.AuthorService;
import com.example.library.Service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping({"/","/books"})
    public String getBooks(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.ListAll();
        List<Author> authors = this.authorService.listAll();
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);

        return "books";
    }

    @DeleteMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        if(this.bookService.findById(id).isPresent()){
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.listAll();
            Category[] categories = Category.values();
            model.addAttribute("authors", authors);
            model.addAttribute("book", book);
            model.addAttribute("categories", categories);
            return "add-book";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @GetMapping("/books/add-form")
    public String addBook(Model model){
        List<Author> authors = this.authorService.listAll();
        Category[] categories = Category.values();
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        return "add-book";
    }

    @PostMapping("/books/taken/{id}")
    public String takeBook(@PathVariable Long id){
        this.bookService.takenById(id);
        return "redirect:/books";

    }

    @PostMapping("/books/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam Category category,
                           @RequestParam Long authorId,
                           @RequestParam("availableCopies") int availableCopies){
        if(id != null){
            this.bookService.edit(id, name,category, authorId, availableCopies);
        }
        else{
            this.bookService.save(name, category, authorId, availableCopies);
        }
        return "redirect:/books";
    }

}
