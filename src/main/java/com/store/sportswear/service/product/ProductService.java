package com.store.sportswear.service.product;

import com.store.sportswear.dto.createDto.ProductCreateDto;
import com.store.sportswear.dto.viewDto.ProductViewDto;
import com.store.sportswear.entity.Cart;
import com.store.sportswear.entity.ConfirmedOrder;
import com.store.sportswear.entity.Product;
import com.store.sportswear.request.ConfirmCartRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAll();
    Product getById(int id);
    ProductCreateDto add(ProductCreateDto productCreateDto);
    List<Product> getByproductName(String productName);
    List<Product> getByproductBrand(String productBrand);
    void deleteById(int id);
    void updateByProductDetails(int productId, String productDetails);
    List<Product> slice(Pageable pageable);
    List<ProductViewDto> getDto();
    Cart addToCart(int id);
    List<Cart> getCart();
    void removeFromCart(int id);
    ConfirmedOrder confirmCart(ConfirmCartRequest confirmCartRequest);
    List<ConfirmedOrder> getAllConfirmedOrder();
    ConfirmedOrder getConfirmedOrderById(int id);
    ConfirmedOrder getConfirmedOrderByOrderNumber(Long orderNumber);
    Map<Integer, Object> searchByProduct(String productName);
    void addFavorite(int productId);
    int getNumberOfFavorite(int productId);
    void removeFromFavorites(int productId);
}
