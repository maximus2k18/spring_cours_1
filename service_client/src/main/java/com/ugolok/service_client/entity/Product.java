package com.ugolok.service_client.entity;

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
