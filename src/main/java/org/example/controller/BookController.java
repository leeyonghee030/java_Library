package org.example.controller;

import org.example.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookController {
    /*
     * CRUD
     * Create, Read, Update, Delete
     * 생성, 조회, 수정, 삭제
     */

    private List<Book> books = new ArrayList<>();
    private int nextId = 1;

    // 생성
    public boolean addBook(Book b) {
        b.setBookId(nextId++);
        books.add(b);
        return true;
    }

    // 조회
    public Book getBookById(int id) {
        return null;
    }

    // 수정

    // 삭제
    public boolean deleteBookById(int id) {
        return true;
    }

}
