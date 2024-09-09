package com.hachit.comvanphong.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to the users table
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false) // Foreign key to the menu table
    private Menu menu;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "discount_code", length = 50)
    private String discountCode;

    @Column(name = "status", length = 50, nullable = false)
    private String status;

}