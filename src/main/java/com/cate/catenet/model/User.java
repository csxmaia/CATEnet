package com.cate.catenet.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "registry")
    private String registry;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "document")
    private String document;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "status")
    private String status;

    @Column(name = "status_description")
    private String statusDescription;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "updated_by")
    private String updatedBy;
}
