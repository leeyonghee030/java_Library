package org.example.controller;

import org.example.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookController {
    /*
     *CRUD
     * Create, Read, Update, Delete
     * 생성 조회 수정 삭제
     */
    //생성
    private List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public boolean addBook(Book b) {
        b.setBookId(nextId++);
        books.add(b);

        return true;
    }

    //조회
    //고유번호로 책에 정보를 가져 온다
    public Book getBookById(int id) {
        return null;
    }

    /*chekName 입력값을 받아서 books에 있는 bookName이랑 비교해서
    대조에 참 거짓 값을 주는 함수
     */
    public Book isCheckName(String s) {
        // Book 이라는 자료형으로 result 변수 선언
        Book result = null;
        // books 책 전체 데이터 반복
        for (Book b : books) {
            // 제시한 책 제목과 일치하다면
            if (b.getBookName().equals(s)) {
                // result에 그 값을(객체)를 담음
                result = b;
            }
        }
        return result;
    }

    //수정

    //삭제
    public boolean deleteBookById(int id) {
        return true;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<String> getBooksName() {
        List<String> booksNameList = new ArrayList<>();

        for (Book b : books) {
            booksNameList.add(b.getBookName());
        }

        return booksNameList;

    }


}

