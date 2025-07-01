package az.edu.itbrains.fruitables.services.impls;

import az.edu.itbrains.fruitables.dtos.basket.AddToCart;
import az.edu.itbrains.fruitables.dtos.basket.BasketUserDto;
import az.edu.itbrains.fruitables.models.Basket;
import az.edu.itbrains.fruitables.models.Product;
import az.edu.itbrains.fruitables.models.User;
import az.edu.itbrains.fruitables.repositories.BasketRepository;
import az.edu.itbrains.fruitables.services.BasketService;
import az.edu.itbrains.fruitables.services.ProductService;
import az.edu.itbrains.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ProductService productService;


    @Override
    public boolean addToCart(AddToCart addToCart, String email) {

        try {

            User findUser = userService.findUserByEmail(email);
            Product findProduct = productService.findProductById(addToCart.getProductId());
            Basket findBasketProduct = basketRepository.findByProductIdAndUserId(addToCart.getProductId(), findUser.getId());
            Double quantity = addToCart.getQuantity() == null ? 1 : addToCart.getQuantity();

            if (findBasketProduct == null){

                Basket basket = new Basket();
                basket.setQuantity(quantity);
                basket.setUser(findUser);
                basket.setProduct(findProduct);
                basketRepository.save(basket);
            }else {
                Double totalQuantity = findBasketProduct.getQuantity() + quantity;
                findBasketProduct.setQuantity(totalQuantity);
                basketRepository.save(findBasketProduct);
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<BasketUserDto> getUserBasket(String email) {
        User findUser = userService.findUserByEmail(email);
        List<Basket> basketList = basketRepository.findByUserId(findUser.getId());
        List<BasketUserDto> basketUserDtoList = basketList.stream().map(basket -> modelMapper.map(basket, BasketUserDto.class)).collect(Collectors.toList());
        return basketUserDtoList;
    }

    @Override
    public boolean removeCartItem(Long cartId, String email) {
       try {
           User findUser = userService.findUserByEmail(email);
           Basket findBasketProduct = basketRepository.findById(cartId).orElseThrow();

              basketRepository.delete(findBasketProduct);

           return true;
       }catch (Exception e){
           return false;
       }
    }
}
