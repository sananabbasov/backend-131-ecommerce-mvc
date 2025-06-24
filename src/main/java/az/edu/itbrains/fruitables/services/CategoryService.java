package az.edu.itbrains.fruitables.services;

import az.edu.itbrains.fruitables.dtos.category.*;
import az.edu.itbrains.fruitables.models.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDashboardDto> getDashboardCategories();

    boolean createCategory(CategoryCreateDto categoryCreateDto);

    CategoryUpdateDto getUpdatedCategories(Long id);

    boolean updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);

    CategoryUpdateDto getDeletedCategory(Long id);

    boolean deleteCategory(Long id);

    Category findCategoryById(Long categoryId);

    List<CategoryHomeFilterDto> getCategoryForFilterProducts();

    List<CategoryShopDto> getShopCategories();
}
