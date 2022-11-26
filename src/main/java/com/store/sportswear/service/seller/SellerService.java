package com.store.sportswear.service.seller;

import com.store.sportswear.entity.Seller;

import java.util.List;

public interface SellerService {
    Seller add(Seller seller);
    void deleteById(int id);
    List<Seller> getAll();
    Seller getById(int id);
}
