package com.ugolok.serviceClient.entity;

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
@NamedQueries(
        @NamedQuery(name = "Product.findAllByTitleIgnoringCase",
                    query = "select p from Product p where p.title ilike :filter"
        )
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // если SEQUENCE тут ошибка выйдет!!!, потому что нужно явно указать generator = "product_seq"
    Integer id;
    @Column(name = "c_title")
    String title;
    @Column(name = "c_details")
    String details;
}
