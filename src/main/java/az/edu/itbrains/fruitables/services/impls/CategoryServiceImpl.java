package az.edu.itbrains.fruitables.services.impls;

import az.edu.itbrains.fruitables.dtos.category.*;
import az.edu.itbrains.fruitables.helpers.SeoHelper;
import az.edu.itbrains.fruitables.models.Category;
import az.edu.itbrains.fruitables.repositories.CategoryRepository;
import az.edu.itbrains.fruitables.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDashboardDto> getDashboardCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDashboardDto> categoryDashboardDtoList = categoryList.stream().map(category -> modelMapper.map(category, CategoryDashboardDto.class)).toList();
        return categoryDashboardDtoList;
    }

    @Override
    public boolean createCategory(CategoryCreateDto categoryCreateDto) {
        try {
            Category findCategory = categoryRepository.findByNameIgnoreCase(categoryCreateDto.getName());
            if (findCategory != null){
                return false;
            }
            Category category = new Category();
            category.setName(categoryCreateDto.getName());
            String seoUrl = SeoHelper.createSeoUrl(categoryCreateDto.getName());
            category.setSeoUrl(seoUrl);
            categoryRepository.save(category);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public CategoryUpdateDto getUpdatedCategories(Long id) {
        Category findCategory = categoryRepository.findById(id).orElseThrow();
        CategoryUpdateDto categoryUpdateDto = modelMapper.map(findCategory, CategoryUpdateDto.class);
        return categoryUpdateDto;
    }

    @Override
    public boolean updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        try {
            Category findCategory = categoryRepository.findById(id).orElseThrow();
            findCategory.setName(categoryUpdateDto.getName());
            String seoUrl = SeoHelper.createSeoUrl(categoryUpdateDto.getName());
            findCategory.setSeoUrl(seoUrl);
            categoryRepository.save(findCategory);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public CategoryUpdateDto getDeletedCategory(Long id) {
        Category findCategory = categoryRepository.findById(id).orElseThrow();
        CategoryUpdateDto categoryUpdateDto = modelMapper.map(findCategory, CategoryUpdateDto.class);
        return categoryUpdateDto;
    }

    @Override
    public boolean deleteCategory(Long id) {
        try {
            Category findCategory = categoryRepository.findById(id).orElseThrow();
            categoryRepository.delete(findCategory);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    @Override
    public List<CategoryHomeFilterDto> getCategoryForFilterProducts() {
        List<Category> categoryList = categoryRepository.findAll().stream().limit(4).collect(Collectors.toList());
        List<CategoryHomeFilterDto> categoryHomeFilterDtoList = categoryList.stream().map(category -> modelMapper.map(category, CategoryHomeFilterDto.class)).toList();
        return categoryHomeFilterDtoList;
    }

    @Override
    public List<CategoryShopDto> getShopCategories() {
        List<CategoryShopDto> categories = categoryRepository.getShopCategories();
        return categories;
    }
}
