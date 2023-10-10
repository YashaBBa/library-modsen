package com.modsen.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @Pattern(regexp = "^(?:\\d{3}-)\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d$",
            message = "ISBN must be valid")
    private String ISBN;
    private String title;
    private String genre;
    private String description;
    private String author;

}
