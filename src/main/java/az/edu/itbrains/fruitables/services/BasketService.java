package az.edu.itbrains.fruitables.services;

import az.edu.itbrains.fruitables.dtos.basket.AddToCart;
import az.edu.itbrains.fruitables.dtos.basket.BasketUserDto;

import java.util.List;

public interface BasketService {
    boolean addToCart(AddToCart addToCart, String email);

    List<BasketUserDto> getUserBasket(String email);

    boolean removeCartItem(Long cartId, String email);
}
