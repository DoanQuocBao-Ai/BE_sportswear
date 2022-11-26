package com.store.sportswear.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CREDİT_CARD")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "expirationDate")
    private Date expirationDate;

    @Column(name = "nameAndSurname")
    private String nameAndSurname;


    public CreditCard(String cardNumber, int cvv, Date expirationDate, String nameAndSurname) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.nameAndSurname = nameAndSurname;
    }
}
