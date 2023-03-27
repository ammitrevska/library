package com.example.library.Web.Controller;

import com.example.library.Model.Enums.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @GetMapping
    public String getCategories(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Category[] categories = Category.values();
        model.addAttribute("categories", categories);

        return "categories";
    }
}
