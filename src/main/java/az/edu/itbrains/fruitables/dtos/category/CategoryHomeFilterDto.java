package az.edu.itbrains.fruitables.dtos.category;

import az.edu.itbrains.fruitables.dtos.product.ProductHomeOrganicFilterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryHomeFilterDto {
    private Long id;
    private String name;
    private String seoUrl;
    private List<ProductHomeOrganicFilterDto> products;
}
