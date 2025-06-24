package az.edu.itbrains.fruitables.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private String name;
    private String shortDescription;
    private String description;
    private String photoUrl;
    private Double quantity;
    private Double price;
    private Double discountPrice;
    private Long categoryId;
}
