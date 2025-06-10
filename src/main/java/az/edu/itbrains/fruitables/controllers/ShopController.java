package az.edu.itbrains.fruitables.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ShopController {

    @GetMapping("/shop")
    public String shop(){
        return "shop.html";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart.html";
    }
}
