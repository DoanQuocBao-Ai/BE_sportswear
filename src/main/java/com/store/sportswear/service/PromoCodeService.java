package com.store.sportswear.service;

import com.store.sportswear.entity.PromoCode;
import com.store.sportswear.request.PromoCodeCreateRequest;

import java.util.List;

public interface PromoCodeService {
    String createPromoCode(PromoCodeCreateRequest promoCodeRequest);
    List<PromoCode> getAll();
    PromoCode getById(Long id);
    void deleteById(Long id);
}
