package com.store.sportswear.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SELLER_COMMENT")
public class SellerComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    private EUser EUser;

    @ManyToOne
    private Seller seller;

    public SellerComment(String title, String body, Date createDate) {
        this.title = title;
        this.body = body;
        this.createDate = createDate;
    }
}
