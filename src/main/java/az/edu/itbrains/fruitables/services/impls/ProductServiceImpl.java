package az.edu.itbrains.fruitables.services.impls;

import az.edu.itbrains.fruitables.dtos.product.*;
import az.edu.itbrains.fruitables.models.Category;
import az.edu.itbrains.fruitables.models.Product;
import az.edu.itbrains.fruitables.payloads.PaginationPayload;
import az.edu.itbrains.fruitables.repositories.ProductRepository;
import az.edu.itbrains.fruitables.services.CategoryService;
import az.edu.itbrains.fruitables.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.Size;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDashboardDto> getDashboardProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDashboardDto> productDashboardDtoList = productList.stream().map(product -> modelMapper.map(product, ProductDashboardDto.class)).toList();
        return productDashboardDtoList;
    }

    @Override
    public boolean createProduct(ProductCreateDto productCreateDto) {
        try {
            Product product = new Product();
            product.setName(productCreateDto.getName());
            product.setDescription(productCreateDto.getDescription());
            product.setShortDescription(productCreateDto.getShortDescription());
            product.setPhotoUrl(productCreateDto.getPhotoUrl());
            product.setQuantity(productCreateDto.getQuantity());
            product.setPrice(productCreateDto.getPrice());
            product.setDiscountPrice(productCreateDto.getDiscountPrice());
            Category category = categoryService.findCategoryById(productCreateDto.getCategoryId());
            product.setCategory(category);

            productRepository.save(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ProductUpdateDto getUpdatedProducts(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow();
        ProductUpdateDto productUpdateDto = modelMapper.map(findProduct, ProductUpdateDto.class);
        return productUpdateDto;
    }

    @Override
    public boolean updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        try {
            Product findProduct = productRepository.findById(id).orElseThrow();
            findProduct.setName(productUpdateDto.getName());
            findProduct.setDescription(productUpdateDto.getDescription());
            findProduct.setShortDescription(productUpdateDto.getShortDescription());
            findProduct.setPhotoUrl(productUpdateDto.getPhotoUrl());
            findProduct.setQuantity(productUpdateDto.getQuantity());
            findProduct.setPrice(productUpdateDto.getPrice());
            findProduct.setDiscountPrice(productUpdateDto.getDiscountPrice());
            Category category = categoryService.findCategoryById(productUpdateDto.getCategoryId());
            findProduct.setCategory(category);
            productRepository.save(findProduct);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ProductUpdateDto getDeletedProduct(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow();
        ProductUpdateDto productUpdateDto = modelMapper.map(findProduct, ProductUpdateDto.class);
        return productUpdateDto;
    }

    @Override
    public boolean deleteProduct(Long id) {
        try {
            Product findProduct = productRepository.findById(id).orElseThrow();
            productRepository.delete(findProduct);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<ProductFreshDto> getFreshProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductFreshDto> productFreshDtoList = productList.stream().map(product -> modelMapper.map(product, ProductFreshDto.class)).toList();
        return productFreshDtoList;
    }

    @Override
    public List<ProductFreshDto> getHomeProductFilterDtoList() {
        List<Product> productList = productRepository.findAll().stream().limit(8).toList();
        List<ProductFreshDto> productFreshDtoList = productList.stream().map(product -> modelMapper.map(product, ProductFreshDto.class)).toList();
        return productFreshDtoList;
    }

    @Override
    public PaginationPayload<ProductShopDto> getShopProducts(Integer currentPage) {
        currentPage = currentPage == null ? 1 : currentPage;
        Pageable pageable = PageRequest.of(currentPage-1, 9, Sort.by("id").descending());
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductShopDto> productShopDtoList = products.get().map(product -> modelMapper.map(product, ProductShopDto.class)).toList();

        PaginationPayload<ProductShopDto> paginationPayload = new PaginationPayload<>();
        paginationPayload.setCurrentPage(currentPage);
        paginationPayload.setTotalPage(products.getTotalPages());
        paginationPayload.setModels(productShopDtoList);
        return paginationPayload;
    }

    @Override
    public ProductDetailDto getProductDetail(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow();
        ProductDetailDto productDetailDto = modelMapper.map(findProduct, ProductDetailDto.class);
        return productDetailDto;
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
