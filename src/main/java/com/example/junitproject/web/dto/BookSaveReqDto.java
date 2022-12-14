package com.example.junitproject.web.dto;

import com.example.junitproject.domain.Book;
import lombok.Data;

@Data
public class BookSaveReqDto {
    private String title;
    private String author;

    public Book toEntity(){
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
