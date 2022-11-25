package com.store.sportswear.service.cart;

import com.store.sportswear.entity.Cart;

import java.util.List;

public interface CartService {
    Cart getById(int id);
    void deleteById(int id);
    List<Cart> getAll();
    void add(Cart cart);
}
