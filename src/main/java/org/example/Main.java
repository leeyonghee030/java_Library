package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.controller.BookController;
import org.example.controller.UserController;
import org.example.entity.Book;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;


/*
 프로젝트 도서관
 로그인, 책 대출, 반납, 책 추가, 조회, 수정 , 삭제

 */

import org.example.controller.BookController;

public class Main {
    static BookController bookController = new BookController();
    static UserController userController = new UserController();


    public static void main(String[] args) {
//        initBook();
        //책 시스템 시작
        start();
    }

    public static void start() {
        System.out.println("도서관 프로그램을 시작합니다");
        //사용자 input
        Scanner sc = new Scanner(System.in);
        //while 제어자
        boolean running = true;

        while (running) {
            manual();
            switch (sc.nextLine()) {
                case "1":
                    checkBookProcess(sc);
                    break;
                case "2":
                    addBookProcess(sc);
                    break;
                case "3":
                    System.out.println(3);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    running = true;
            }
        }

        //while 로직 종료
        System.out.println("도서관 프로그램을 종료합니다");
    }

    // 안내문
    public static void manual() {
        //1번 책 등록
        System.out.println("""
                ======================================
                            도서관 프로그램
                ======================================
                1.책 목록 조회
                2.책 추가
                3.책 삭제
                0.종료
                
                입력 :
                """);
        //2번 책 조회
        //3
    }

    public static void addBookProcess(Scanner sc) {
        System.out.println("책이름: ");
        String bookName = sc.nextLine();
        System.out.println("작가이름: ");
        String author = sc.nextLine();
        System.out.println("출판사이름: ");
        String publisher = sc.nextLine();

        Book book = new Book(bookName, author, publisher);

        if (bookController.addBook(book)) {
            System.out.println("책 추가 되었습니다");
        } else {
            System.out.println("다시 시도하세요");
        }


    }

    // 조회에 대한 안내 및 조회 기능 선택 메소드
    public static void checkBookProcess(Scanner sc) {
        System.out.println("조회 기능을 선택해 주세요");
        System.out.println("1.전체 조회");
        System.out.println("2.제목으로 책 조회");
        int checkNam = sc.nextInt();
        sc.nextLine();

        switch (checkNam) {
            case 1: {
                System.out.println(bookController.getBooks());
                break;
            }
            case 2: {
                System.out.println("정보 조회할 책 이름을 적어주세요");
                String checkName = sc.nextLine();
                 Book nameResult = bookController.isCheckName(checkName);
                 if (nameResult == null){
                     System.out.println("책 정보 없음");
                 } else {
                     System.out.println(nameResult);
                 }
                break;
            }
            default:
                System.out.println("알맞은 숫자를 입력하세요");

        }


    }


    // ------------------------------------------------------------------------------------------------------------
    public static void initBook() {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Book>>() {
        }.getType();

        InputStream is = Main.class.getResourceAsStream("/books.json");
        List<Book> books = gson.fromJson(new InputStreamReader(is), listType);

        for (Book book : books) {
            bookController.addBook(book);
            System.out.println("책 추가됨: " + book);
        }
    }


}
