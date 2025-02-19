package com.ugolok.manager_app.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
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
