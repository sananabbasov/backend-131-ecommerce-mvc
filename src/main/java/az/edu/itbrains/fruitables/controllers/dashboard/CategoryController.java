package az.edu.itbrains.fruitables.controllers.dashboard;

import az.edu.itbrains.fruitables.dtos.category.CategoryCreateDto;
import az.edu.itbrains.fruitables.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.fruitables.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.fruitables.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/dashboard/categories")
    @PreAuthorize("isAuthenticated()")
    public String index(Model model){

        List<CategoryDashboardDto> categoryDashboardDtoList = categoryService.getDashboardCategories();
        model.addAttribute("categories", categoryDashboardDtoList);
        return "dashboard/category/index.html";
    }


    @GetMapping("/dashboard/category/create")
    @PreAuthorize("isAuthenticated()")
    public String create(){
        return "dashboard/category/create.html";
    }

    @PostMapping("/dashboard/category/create")
    @PreAuthorize("isAuthenticated()")
    public String create(CategoryCreateDto categoryCreateDto){
        boolean result = categoryService.createCategory(categoryCreateDto);
        if (result){
            return "redirect:/dashboard/categories";
        }
        return "dashboard/category/create.html";
    }

    @GetMapping("/dashboard/category/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public String update(@PathVariable Long id, Model model){
        CategoryUpdateDto categoryUpdateDto = categoryService.getUpdatedCategories(id);
        model.addAttribute("category", categoryUpdateDto);
        return "dashboard/category/update.html";
    }

    @PostMapping("/dashboard/category/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public String update(@PathVariable Long id, CategoryUpdateDto categoryUpdateDto){
        boolean result = categoryService.updateCategory(id, categoryUpdateDto);
        if (result){
            return "redirect:/dashboard/categories";
        }
        return "dashboard/category/update.html";
    }

    @GetMapping("/dashboard/category/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String delete(@PathVariable Long id, Model model){
        CategoryUpdateDto categoryUpdateDto = categoryService.getDeletedCategory(id);
        model.addAttribute("category", categoryUpdateDto);
        return "dashboard/category/delete.html";
    }

    @PostMapping("/dashboard/category/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String delete(@PathVariable Long id){
        boolean result = categoryService.deleteCategory(id);
        if (result){
            return "redirect:/dashboard/categories";
        }
        return "dashboard/category/delete.html";
    }
}
