package com.example.library.Web.Rest;

import com.example.library.Model.Book;
import com.example.library.Model.dto.BookDto;
import com.example.library.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    //read
    @GetMapping()
    public List<Book> findAll(){
        return this.bookService.ListAll();
    }
//
//    @GetMapping
//    public ResponseEntity<Book> findById(@PathVariable Long id){
//        return this.bookService.findById(id)
//                .map(book -> ResponseEntity.ok().body(book))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

    //create

    //Todo
    //update
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto){
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //Mark as Taken
    @PostMapping("/taken/{id}")
    public void taken(@PathVariable Long id){
        this.bookService.takenById(id);
    }

}
