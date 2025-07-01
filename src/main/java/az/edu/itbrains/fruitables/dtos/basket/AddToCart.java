package az.edu.itbrains.fruitables.dtos.basket;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddToCart {
    private Long productId;
    private Double quantity;
}
