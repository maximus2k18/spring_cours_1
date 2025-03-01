package com.ugolok.managerApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(schema = "catalog", name = "t_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // тут ошибка выйдет!!!, потому что нужно явно указать generator = "product_seq"
    Integer id;
    @Column(name = "c_title")
    String title;
    @Column(name = "c_details")
    String details;
}