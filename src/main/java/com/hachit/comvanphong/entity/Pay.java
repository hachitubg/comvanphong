package com.hachit.comvanphong.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pay")
@Data
public class Pay extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "payment_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal paymentAmount;

    @Column(name = "confirmation_image", length = 255)
    private String confirmationImage;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

}
