package com.hachit.comvanphong.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@Data
public class Image extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "entity_type", nullable = false, length = 50)
    private String entityType;

    @Column(name = "image_url", nullable = false, length = 255)
    private String imageUrl;

}
