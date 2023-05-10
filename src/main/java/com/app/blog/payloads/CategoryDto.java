package com.app.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    @NotEmpty
    @Size(min=4,message = "Min Size of title is 4")
    private String categoryTitle;
    @NotEmpty
    @Size(min=4,message = "Min Size of Desc is 4")
    private String categoryDescription;
}
