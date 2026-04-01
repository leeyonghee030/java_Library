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
        for (Book b : books) {
            if (b.getBookId() == id) {
                return b;
            }
        }
        return null;
    }

    // 책이름으로 조회
    public List<Book> getBookByName(String name) {
        List<Book> searchBookList = new ArrayList<>();
        for (Book b : books) {
            if (b.getBookName().equals(name)) {
                searchBookList.add(b);
            }
        }
        return searchBookList;
    }

    // 수정
    public boolean updateBook(Book b) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == b.getBookId()) {
                books.set(i, b);
                return true;
            }
        }
        return false;
    }

    // 삭제
    public boolean deleteBookById(int id) {
        return true;
    }

    public List<Book> getBooks() {
        return books;
    }
}
