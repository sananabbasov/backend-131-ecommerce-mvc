package az.edu.itbrains.fruitables.dtos.product;

import az.edu.itbrains.fruitables.dtos.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFreshDto {
    private Long id;
    private String name;
    private String shortDescription;
    private String photoUrl;
    private Double price;
    private CategoryDto category;
}
