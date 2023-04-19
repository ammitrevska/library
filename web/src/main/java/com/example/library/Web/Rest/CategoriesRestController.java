package com.example.library.Web.Rest;

import com.example.library.Model.Enums.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoriesRestController {

    //read
    @GetMapping
    public List<Category> findAll() {
        Category[] categories = Category.values();

        return List.of(categories);
    }
}
