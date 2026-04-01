package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.controller.BookController;
import org.example.entity.Book;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
            switch (sc.nextLine()) {
                case "1":
                    findBookProcess(sc);
                    break;
                case "2":
                    addBookProcess(sc);
                    break;
                case "3":
                    break;
                case "4":
                    updateBookProcess(sc);
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
                4. 책 정보 수정
                0. 종료
                입력 :""");
    }

    // 책 추가 서비스
    public static void addBookProcess(Scanner sc) {
        System.out.print("책 이름: ");
        String bookName = sc.nextLine();
        System.out.print("작가: ");
        String author = sc.nextLine();
        System.out.print("출판사: ");
        String publisher = sc.nextLine();

        Book book = new Book(bookName, author, publisher);
        if (bookController.addBook(book)) {
            System.out.println("추가 되었습니다");
        } else {
            System.out.println("다시 시도해주세요");
        }
    }

    // 책 조회 서비스
    public static void findBookProcess(Scanner sc) {
        int process;
        String searchName;
        System.out.println("""
                1. 전체 조회
                2. 제목으로 조회
                입력: 
                """);
        process = sc.nextInt();
        sc.nextLine();
        switch (process) {
            case 1:
                System.out.println(bookController.getBooks());
                break;
            case 2:
                System.out.println("책 이름을 입력해주세요 : ");
                searchName = sc.nextLine();
                List<Book> result = bookController.getBookByName(searchName);
                if (result.isEmpty()) {
                    System.out.println("책 제목을 찾을 수 없습니다");
                } else {
                    System.out.println(result);
                }
                break;
            default:
                System.out.println("숫자를 입력하세요");
        }

    }

    private static void updateBookProcess(Scanner sc) {
        int process;
        System.out.println("""
                1. 고유번호로 수정
                2. 책 제목으로 수정
                """);
        process = sc.nextInt();
        sc.nextLine();
        switch (process) {
            case 1: {
                Book b = null;
                while (b == null) {
                    System.out.println("고유번호를 입력하세요 (0: 취소)");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (id == 0) return;
                    b = bookController.getBookById(id);
                    if (b == null) System.out.println("존재하지 않는 번호입니다. 다시 입력하세요.");
                }
                System.out.println("수정할 책 제목 입력 현재 :" + b.getBookName());
                String bookName = sc.nextLine();
                System.out.println("수정할 작가 입력 현재 :" + b.getAuthor());
                String author = sc.nextLine();
                System.out.println("수정할 출판사 입력 현재 :" + b.getPublisher());
                String publisher = sc.nextLine();
                Book editedBook = new Book(bookName, author, publisher);
                editedBook.setBookId(b.getBookId());
                if (bookController.updateBook(editedBook)) {
                    System.out.println("수정 되었습니다");
                    System.out.println(bookController.getBookById(editedBook.getBookId()));
                }
                break;
            }
            case 2: {
                List<Book> b = new ArrayList<>();  // Bug 1: null → ArrayList
                Book prevBook = null;
                int id;
                String searchName;
                while (b.isEmpty()) {
                    System.out.println("책 제목을 입력하세요 (0 : 취소)");
                    searchName = sc.nextLine();
                    if (searchName.equals("0")) return;
                    b = bookController.getBookByName(searchName);  // Bug 2: searchName 사용
                    if (b.isEmpty()) System.out.println("존재하지 않는 제목입니다 다시 입력하세요");
                }
                System.out.println("조회 된 책 목록" + b);

                if (b.size() == 1) {  // Bug 6: 단일 결과 처리 추가
                    prevBook = b.get(0);
                } else {
                    System.out.println("수정할 고유번호를 입력해주세요");
                    id = sc.nextInt();
                    sc.nextLine();
                    prevBook = bookController.getBookById(id);
                    if (prevBook == null) {
                        System.out.println("잘 못 입력하였습니다");
                        return;  // Bug 3: null 체크 후 return
                    }
                }
                System.out.println("수정할 책 제목 입력 현재 :" + prevBook.getBookName());
                String bookName = sc.nextLine();
                System.out.println("수정할 작가 입력 현재 :" + prevBook.getAuthor());
                String author = sc.nextLine();
                System.out.println("수정할 출판사 입력 현재 :" + prevBook.getPublisher());
                String publisher = sc.nextLine();
                Book editedBook = new Book(bookName, author, publisher);
                editedBook.setBookId(prevBook.getBookId());  // Bug 4: bookId 설정
                if (bookController.updateBook(editedBook)) {
                    System.out.println("수정 되었습니다");
                    System.out.println(bookController.getBookById(editedBook.getBookId()));
                }
                break;  // Bug 5: break 추가
            }
            default:
                System.out.println("숫자를 입력하세요");
        }
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


