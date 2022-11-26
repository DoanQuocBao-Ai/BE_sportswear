package com.store.sportswear.request;

import lombok.Data;

import java.util.Date;

@Data
public class ConfirmCartRequest {

    private int id;

    private String cardNumber;

    private int cvv;

    private Date expirationDate;

    private String nameAndSurname;

    private String promoCode;
}
