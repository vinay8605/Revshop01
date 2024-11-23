package com.revshop1.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @Column(name = "buyer_id", nullable = false)
    private int buyerId;

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;  

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "added_at", nullable = false, updatable = false)
    private Timestamp addedAt;

    @PrePersist
    protected void onCreate() {
        this.addedAt = new Timestamp(System.currentTimeMillis());
    }

   

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }




}
