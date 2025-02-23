package com.ugolok.service_client.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@NoArgsConstructor()
@Getter
@Setter
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
