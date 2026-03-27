package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.controller.BookController;
import org.example.entity.Book;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
     * 프로젝트: 도서관
     * 기능
     * 회원가입, 로그인, 책 대출 반납, 책 추가, 조회, 수정, 삭제
     */

    static BookController bookController = new BookController();

    public static void main(String[] args) {
//        initBook();

        // 책 시스템 시작
        System.out.println("도서관 프로그램을 시작합니다.");
        start();
        System.out.println("도서관 프로그램을 종료합니다.");

    }


    public static void start() {
        // 사용자 input
        Scanner sc = new Scanner(System.in);
        // while 제어자
        boolean running = true;
        while (running) {
            manual();
            switch (sc.next()) {
                case "1":
                    break;
                case "2":
                    addBookProcess(sc);
                case "3":
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    running = true;
            }
        }
    }



    public static void manual() {
        System.out.println("""
                ============================
                        도서관 프로그램 
                ============================
                1. 책 목록 조회
                2. 책 추가
                3. 책 삭제
                0. 종료
                입력 :""");
    }

    public static void addBookProcess(Scanner sc) {
        System.out.print("책 이름: ");
        String bookName = sc.nextLine();
        System.out.print("작가: ");
        String author = sc.nextLine();
        System.out.print("출판사: ");
        String publisher = sc.nextLine();

        Book book = new Book(bookName, author, publisher);
        bookController.addBook(book);
    }

}


// ---------------------------------------------------------------------------------------
//public static void initBook() {
//    Gson gson = new Gson();
//    Type listType = new TypeToken<List<Book>>() {
//    }.getType();
//
//    InputStream is = Main.class.getResourceAsStream("/books.json");
//    List<Book> books = gson.fromJson(new InputStreamReader(is), listType);
//
//    for (Book book : books) {
//        bookController.addBook(book);
//        System.out.println("책 추가됨: " + book);
//    }
//}


