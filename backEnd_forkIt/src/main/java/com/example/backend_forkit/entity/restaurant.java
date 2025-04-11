package com.example.backend_forkit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
public class restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;

    private String address;

    private String cuisineType;

    private String description;

    private LocalDateTime createdAt;

    private String priceRange;

    private Double averageRating;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private user owner;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<comment> comments = new ArrayList<>();
}
