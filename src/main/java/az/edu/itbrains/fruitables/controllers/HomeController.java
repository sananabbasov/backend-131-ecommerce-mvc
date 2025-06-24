package az.edu.itbrains.fruitables.controllers;


import az.edu.itbrains.fruitables.dtos.category.CategoryHomeFilterDto;
import az.edu.itbrains.fruitables.dtos.product.ProductFreshDto;
import az.edu.itbrains.fruitables.services.CategoryService;
import az.edu.itbrains.fruitables.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping("/")
    public String index(Model model){

        List<ProductFreshDto> productFreshDtoList = productService.getFreshProducts();
        List<ProductFreshDto> productOrganicDtoList = productService.getHomeProductFilterDtoList();
        List<CategoryHomeFilterDto> categoryHomeFilterDtoList = categoryService.getCategoryForFilterProducts();
        model.addAttribute("freshProducts",productFreshDtoList);
        model.addAttribute("organicCategories",categoryHomeFilterDtoList);
        model.addAttribute("organicProducts",productOrganicDtoList);
        return "index.html";
    }


    @GetMapping("/contact")
    public String contact(){
        return "contact.html";
    }

}
