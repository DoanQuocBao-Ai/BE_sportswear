package com.store.sportswear.request;

import lombok.Data;

@Data
public class ProductDetailsUpdateRequest {

    private int productId;

    private String productDetails;
}
