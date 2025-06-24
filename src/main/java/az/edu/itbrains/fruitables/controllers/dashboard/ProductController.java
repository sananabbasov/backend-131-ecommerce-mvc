package az.edu.itbrains.fruitables.controllers.dashboard;

import az.edu.itbrains.fruitables.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.fruitables.dtos.product.ProductCreateDto;
import az.edu.itbrains.fruitables.dtos.product.ProductDashboardDto;
import az.edu.itbrains.fruitables.dtos.product.ProductUpdateDto;
import az.edu.itbrains.fruitables.services.CategoryService;
import az.edu.itbrains.fruitables.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/dashboard/products")
    public String index(Model model){

        List<ProductDashboardDto> productDashboardDtoList = productService.getDashboardProducts();
        model.addAttribute("products", productDashboardDtoList);
        return "dashboard/product/index.html";
    }


    @GetMapping("/dashboard/product/create")
    public String create(Model model){
        List<CategoryDashboardDto> categoryDashboardDtoList = categoryService.getDashboardCategories();
        model.addAttribute("categories", categoryDashboardDtoList);
        return "dashboard/product/create.html";
    }

    @PostMapping("/dashboard/product/create")
    public String create(ProductCreateDto productCreateDto, Model model){
        List<CategoryDashboardDto> categoryDashboardDtoList = categoryService.getDashboardCategories();
        model.addAttribute("categories", categoryDashboardDtoList);
        boolean result = productService.createProduct(productCreateDto);
        if (result){
            return "redirect:/dashboard/products";
        }
        return "dashboard/product/create.html";
    }

    @GetMapping("/dashboard/product/update/{id}")
    public String update(@PathVariable Long id, Model model){
        List<CategoryDashboardDto> categoryDashboardDtoList = categoryService.getDashboardCategories();
        model.addAttribute("categories", categoryDashboardDtoList);
        ProductUpdateDto productUpdateDto = productService.getUpdatedProducts(id);
        model.addAttribute("product", productUpdateDto);
        return "dashboard/product/update.html";
    }

    @PostMapping("/dashboard/product/update/{id}")
    public String update(@PathVariable Long id, ProductUpdateDto productUpdateDto, Model model){
        List<CategoryDashboardDto> categoryDashboardDtoList = categoryService.getDashboardCategories();
        model.addAttribute("categories", categoryDashboardDtoList);
        boolean result = productService.updateProduct(id, productUpdateDto);
        if (result){
            return "redirect:/dashboard/products";
        }
        return "dashboard/product/update.html";
    }

    @GetMapping("/dashboard/product/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        ProductUpdateDto productUpdateDto = productService.getDeletedProduct(id);
        model.addAttribute("product", productUpdateDto);
        return "dashboard/product/delete.html";
    }

    @PostMapping("/dashboard/product/delete/{id}")
    public String delete(@PathVariable Long id){
        boolean result = productService.deleteProduct(id);
        if (result){
            return "redirect:/dashboard/products";
        }
        return "dashboard/product/delete.html";
    }
}
