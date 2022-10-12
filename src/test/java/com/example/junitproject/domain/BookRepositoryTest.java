package com.example.junitproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private String title;
    private String author;
    Book book;
    Book bookPs;

    @BeforeEach
    public void preTest(){
        title = "junit5";
        author = "MetaCoding";
        book = Book.builder()
                .title(title)
                .author(author)
                .build();

        bookPs = bookRepository.save(book);
    }

    // 1. 책 등록
    @Test
    public void saveTest(){
        // given 테이터 준비

        // when  테스트 실행

        // then 검증
        assertEquals(title, bookPs.getTitle());
        assertEquals(author, bookPs.getAuthor());

    }
    // 2. 책 목록 보기
    @Test
    public void checkListTest(){
        //given

        //when
        List<Book> bookList = bookRepository.findAll();

        //then
        assertEquals(title, bookList.get(0).getTitle());
        assertEquals(author, bookList.get(0).getAuthor());
    }
    // 3. 책 한건 보기
    @Test
    public void checkOneListTest(){
        //given

        //when
        Book bookOne = bookRepository.findById(1L).get();

        //then
        assertEquals(title, bookOne.getTitle());
        assertEquals(author, bookOne.getAuthor());

    }
    // 4. 책 수정
    // 5. 책 삭제
}
