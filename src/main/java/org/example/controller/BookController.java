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
    //Book을 저장하기 위해 ArrayList 객체(books)를 만듬
    private int nextId = 1;
    //id 숫자를 위해 변수 생성

    //Book타입 변수 b를 받아 b에 BookId를 추가한 후 books 리스트에 b를 추가한다
    public boolean addBook(Book b) {
        b.setBookId(nextId++);
        //BookID 자동으로 생성을 위해 nextId++를 사용하여 한번 작동후
        // 자동 숫자를 올라가도록 만듬
        books.add(b);

        return true; //반환값 boolean 으로 true 반환값을줌
    }

    //조회
    //고유번호로 책에 정보를 가져 온다
    public Book getBookById(int id) {
        return null;
    }

    /*chekName 입력값을 받아서 books에 있는 bookName이랑 비교해서
    대조에 참 거짓 값을 주는 함수
     */
    public List<Book> isCheckName(String s) {
        // Book 이라는 자료형으로 result 변수 선언
        List<Book> resultList = new ArrayList<>();
        // books 책 전체 데이터 반복
        for (Book b : books) {
            // 제시한 책 제목과 일치하다면(String 같은 객체를 비교할 때
            // == 대신 equals를 씀)
            if (b.getBookName().equals(s)) {
                // result에 그 값을(객체)를 담음
                resultList.add(b); // 한값만 나옴 (만약 같은 이름이 있을경우를 위해
                //List로 하는게 좋음
            }
        }
        return resultList;
    }

    //수정
    // Book으로 반환
    public Book isCheckId(int s) {
        Book checkId = null; //if 조건문을 위해 null값으로
        for (Book i : books) {//books 처음부터 마지막객체까지 반복 i에 books값 저장)
            if (i.getBookId() == s) {//books안에있는 id s랑 비교했을떄 대조되면
                checkId = i;   // id에 대조된 Book 객체 저장
            }
        }
        return checkId;
    }

    public Book isEditBook(int b, String n, String a, String p) {
      Book editBook = null;
        for (Book e : books) { //e는 books 안의 객체를 그대로 가리킴
            if (e.getBookId()== b){ //id가 일치할떄 name,author,publisher
                e.setBookName(n); //을 set으로 수정한다 매개변수값으로
                e.setAuthor(a);
                e.setPublisher(p);
                editBook = e; // 변경된 e값을 저장
            }
        } return editBook;
    }

    //검색결과가 1개일떄 사용하는 수정 메서드
    public Book isEditBookName(List<Book> b,String n, String a, String p) {
        Book editBook = null;
        for (Book e : books) {   //매개변수 그전에 이름 체크로 1회확인되었음
            if (e.getBookName().equals(b)){ //매개변수를 책제목으랑 비교
                e.setBookName(n); //set으로 이름들 수정
                e.setAuthor(a);
                e.setPublisher(p);
                editBook = e;  //대조된 Bokk객체 수정된 값 변수에저장
            }
        } return editBook;
    }


    //삭제
    public boolean deleteBookById(int id) {
        return true;
    }

    //books가 private라서 외부에서 직접 접근 할수없다
    //books 리스트 전체를 외부에서 조회할수있도록 만든 메소드
    //getBooks으로 books를 돌려받아 books 반환
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

