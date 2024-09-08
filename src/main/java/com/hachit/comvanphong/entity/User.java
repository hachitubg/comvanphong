package com.hachit.comvanphong.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(name = "office_address", nullable = false, length = 255)
    private String officeAddress;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "role", nullable = false, length = 20)
    private String role = "USER";

    @Column(name = "user_authentic", columnDefinition = "int default 0")
    private int userAuthentic;

    @Column(name = "building", length = 100)
    private String building;

    @Column(name = "floors", length = 100)
    private String floors;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "ins_dtm", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime insDtm;

    @Column(name = "upd_dtm", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updDtm;

    @Column(name = "del_yn", columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char delYn;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;
}