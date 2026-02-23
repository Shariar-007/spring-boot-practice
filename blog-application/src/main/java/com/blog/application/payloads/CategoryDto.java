package com.blog.application.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;

    @NotEmpty
    @Size(min = 4, message = "Category title must have minimum size 4 !!")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10, message = "Category description must have minimum size 10 !!")
    private String categoryDetails;
}
