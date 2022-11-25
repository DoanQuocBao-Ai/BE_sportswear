package com.store.sportswear.service.product;

import com.store.sportswear.entity.ProductComment;

import java.util.List;

public interface ProductCommentService {
    ProductComment add(ProductComment productComment);
    void deleteById(int id);
    ProductComment getById(int id);
    List<ProductComment> getAll();
    List<ProductComment> getCommentsByProduct(int id);
}
