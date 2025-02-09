package com.ugolok.spring_cours_24.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor()
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    Integer id;
    String title;
    String details;
}
