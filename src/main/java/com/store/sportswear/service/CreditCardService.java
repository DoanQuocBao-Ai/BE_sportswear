package com.store.sportswear.service;

import com.store.sportswear.entity.CreditCard;

import java.util.List;

public interface CreditCardService {
    List<CreditCard> getAll();
    CreditCard add(CreditCard creditCard);
    List<CreditCard> getCreditCardByUserId(int id);
    CreditCard getBydId(int id);
}
