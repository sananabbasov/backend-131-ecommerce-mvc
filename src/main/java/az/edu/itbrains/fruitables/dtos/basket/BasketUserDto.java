package az.edu.itbrains.fruitables.dtos.basket;


import az.edu.itbrains.fruitables.dtos.product.ProductBasketDto;
import az.edu.itbrains.fruitables.dtos.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketUserDto {
    private Long id;
    private ProductBasketDto product;
    private Double quantity;
    private Double totalPrice;

    public Double getTotalPrice() {
        return quantity * product.getPrice();
    }
}
