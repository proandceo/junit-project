package com.example.junitproject.service;

import com.example.junitproject.domain.Book;
import com.example.junitproject.domain.BookRepository;
import com.example.junitproject.web.dto.BookRespDto;
import com.example.junitproject.web.dto.BookSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto saveBooks(BookSaveReqDto dto) {
        Book bookPS = bookRepository.save(dto.toEntity());
        return new BookRespDto().toDto(bookPS);
    }

    public List<BookRespDto> listBook(){
        return bookRepository.findAll().stream()
                .map(new BookRespDto()::toDto)
                .collect(Collectors.toList());
    }

    public BookRespDto listOneBook(Long id) {
        Optional<Book> bookOp = bookRepository.findById(id);
        if (bookOp.isPresent()) {
            return new BookRespDto().toDto(bookOp.get());
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
