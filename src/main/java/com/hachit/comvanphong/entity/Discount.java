package com.hachit.comvanphong.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "discount")
@Data
public class Discount extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "deadline")
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "use", columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char use;

}