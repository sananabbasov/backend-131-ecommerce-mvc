package az.edu.itbrains.fruitables.dtos.category;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryShopDto {
    private Long id;
    private String name;
    private Long count;
}
