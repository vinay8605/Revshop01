package com.revshop1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
public class Wishlist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "buyer_id", nullable = false)
    private int buyerId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;

    
    public Wishlist() {}

    public Wishlist(int buyerId, int productId, LocalDateTime addedAt) {
        this.buyerId = buyerId;
        this.productId = productId;
        this.addedAt = addedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
