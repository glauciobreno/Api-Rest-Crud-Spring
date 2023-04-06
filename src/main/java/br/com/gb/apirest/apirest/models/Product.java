package br.com.gb.apirest.apirest.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer price;

    @Getter
    @Setter
    @Column
    private String description;

    }

