package com.hachit.comvanphong.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "groups")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "building", length = 100)
    private String building;

    @Column(name = "floors", length = 100)
    private String floors;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "ins_dtm", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime insDtm;

    @Column(name = "upd_dtm", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updDtm;

    @Column(name = "del_yn", columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char delYn;

    // Relationships
    @ManyToMany(mappedBy = "groups")
    private List<User> users;
}

