package az.edu.itbrains.fruitables.services;

import az.edu.itbrains.fruitables.dtos.product.*;
import az.edu.itbrains.fruitables.models.Product;
import az.edu.itbrains.fruitables.payloads.PaginationPayload;

import java.util.List;

public interface ProductService {
    List<ProductDashboardDto> getDashboardProducts();

    boolean createProduct(ProductCreateDto productCreateDto);

    ProductUpdateDto getUpdatedProducts(Long id);

    boolean updateProduct(Long id, ProductUpdateDto productUpdateDto);

    ProductUpdateDto getDeletedProduct(Long id);

    boolean deleteProduct(Long id);

    List<ProductFreshDto> getFreshProducts();

    List<ProductFreshDto> getHomeProductFilterDtoList();

    PaginationPayload<ProductShopDto> getShopProducts(Integer currentPage);

    ProductDetailDto getProductDetail(Long id);

    Product findProductById(Long productId);

    }
