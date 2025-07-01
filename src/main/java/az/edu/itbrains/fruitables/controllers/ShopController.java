package az.edu.itbrains.fruitables.controllers;

import az.edu.itbrains.fruitables.dtos.basket.AddToCart;
import az.edu.itbrains.fruitables.dtos.basket.BasketUserDto;
import az.edu.itbrains.fruitables.dtos.category.CategoryShopDto;
import az.edu.itbrains.fruitables.dtos.product.ProductDetailDto;
import az.edu.itbrains.fruitables.dtos.product.ProductShopDto;
import az.edu.itbrains.fruitables.payloads.PaginationPayload;
import az.edu.itbrains.fruitables.services.BasketService;
import az.edu.itbrains.fruitables.services.CategoryService;
import az.edu.itbrains.fruitables.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BasketService basketService;

    @GetMapping("/shop")
    public String shop(Model model, Integer currentPage){

        PaginationPayload<ProductShopDto> productShopDtoPaginationPayload = productService.getShopProducts(currentPage);
        List<CategoryShopDto> categoryShopDtoList = categoryService.getShopCategories();
        model.addAttribute("products",productShopDtoPaginationPayload);
        model.addAttribute("categories",categoryShopDtoList);
        return "shop.html";
    }

    @GetMapping("/shop/detail/{id}")
    public String detail(Model model, @PathVariable Long id){

        ProductDetailDto productDetailDto = productService.getProductDetail(id);
        List<CategoryShopDto> categoryShopDtoList = categoryService.getShopCategories();

        model.addAttribute("categories",categoryShopDtoList);
        model.addAttribute("product",productDetailDto);
        return "detail.html";
    }


    @GetMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public String cart(Principal principal, Model model){
        String email = principal.getName();
        List<BasketUserDto> basketUserDtoList = basketService.getUserBasket(email);
        model.addAttribute("baskets", basketUserDtoList);
        return "cart.html";
    }


    @PostMapping("/addToCart")
    @PreAuthorize("isAuthenticated()")
    public String addToCart(AddToCart addToCart, Principal principal){
        String email = principal.getName();
        boolean result = basketService.addToCart(addToCart, email);
        return "redirect:/cart";
    }

    @GetMapping("/removeFromCart/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteCart(@PathVariable Long id, Principal principal){
        String email = principal.getName();
        boolean result = basketService.removeCartItem(id, email);
        return "redirect:/cart";
    }
}
