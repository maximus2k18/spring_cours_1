package com.ugolok.serviceClient.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProduct (
        @NotNull(message = "{catalog.products.create.errors.title_is_null}")
        @Size(min = 3, max = 50, message = "{catalog.products.create.errors.title_size_is_invalid}")
        String title,
        @Size(max = 100, message = "{catalog.products.create.errors.details_size_is_invalid}")
        String details
){}
