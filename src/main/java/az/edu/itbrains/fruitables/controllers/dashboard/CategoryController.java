package az.edu.itbrains.fruitables.controllers.dashboard;

import az.edu.itbrains.fruitables.dtos.category.CategoryCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {


    @GetMapping("/dashboard/categories")
    public String index(){
        return "dashboard/category/index.html";
    }


    @GetMapping("/dashboard/category/create")
    public String create(){
        return "dashboard/category/create.html";
    }

    @PostMapping("/dashboard/category/create")
    public String create(CategoryCreateDto categoryCreateDto){
        return "dashboard/category/create.html";
    }

}
