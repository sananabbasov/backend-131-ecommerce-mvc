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
public class ProductDashboardDto {
    private Long id;
    private String name;
    private String photoUrl;
    private Double quantity;
    private Double price;
    private Double discountPrice;
    private CategoryDto category;
}
