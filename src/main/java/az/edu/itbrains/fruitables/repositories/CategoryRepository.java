package az.edu.itbrains.fruitables.repositories;

import az.edu.itbrains.fruitables.dtos.category.CategoryShopDto;
import az.edu.itbrains.fruitables.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByNameIgnoreCase(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM shopCategories")
    List<CategoryShopDto> getShopCategories();
}
