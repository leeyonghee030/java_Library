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
    //BookController 객체를 생성 하여 Main 클래스 전체에서 사용할수있도록 선언
    static BookController bookController = new BookController();
    static UserController userController = new UserController();


    public static void main(String[] args) {
//        initBook();
        //책 시스템 시작
        System.out.println("도서관 프로그램을 시작합니다");
        //책 시스템에 대한 모든 메소드
        start();
        //시스템 종료
        System.out.println("도서관 프로그램을 종료합니다");
    }

    public static void start() {
        //사용자 input

        //사용자 입력(System.in)을 받기 위한 Scanner 객체생성
        Scanner sc = new Scanner(System.in);
        //while 제어자
        boolean running = true;
//사용자가 종료 원하기전 까지 반복문
        while (running) {
            //책시스템 시작에 앞서 사용법 안내
            manual();
            switch (sc.nextLine()) {
                //next(),nextInt() 는 입력값만 가져가고 엔터(줄바꿈)을 남겨두어서
                //다음 nextLine()이 그 엔터를 읽어서 입력을 건너뛰는거 처럼보임
                //해결방법으로 next(),nextInt() 쓴후 nextLine()을 한번 넣어 비워줌
                case "1":
                    checkBookProcess(sc);
                    //책 조회를 위한 메소드
                    break;
                case "2":
                    addBookProcess(sc);
                    //책 추가를 위한 메소드
                    break;
                case "3":
                    etaBookProcess(sc);
                    //책 수정을 위한 메소드
                    break;
                case "4":
                    editBookNameProcess(sc);
                    //책제목으로 수정하는 메소드
                    break;

                case "0":
                    running = false;
                    //반복문 종료를 위해
                    break;
                default: //어떤 case에도 해당 되지않을떄 실행(반복하기위해)
                    System.out.println("잘못된 입력입니다.");
                    System.out.println("숫자만 입력해주세요");
                    //running = true; //어짜피 true여서 할필요없음

            }
        }

        //while 로직 종료
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
                3.책 고유번호로 수정
                4.책 이름으로 수정
                0.종료
                
                입력 :
                """);
        // """ """ 를 쓰면 \n 없이 보이는 그대로 작성가능 (여러줄 문자열)

    }

    //책추가 하는 메소드
    public static void addBookProcess(Scanner sc) {
        System.out.println("책이름: ");
        String bookName = sc.nextLine();
        //책이름값 사용자에게 받아서 bookName에 저장
        System.out.println("작가이름: ");
        String author = sc.nextLine();
        //작가이름 사용자에게 받아서 author에 저장
        System.out.println("출판사이름: ");
        String publisher = sc.nextLine();

        Book book = new Book(bookName, author, publisher);
        //위에 값을 받아 새로운 Book 객체를 생성

       //bookControㅣler안에 addBook(book) 함수 실행 (반환값을 받아 작용)
        //addBook함수에 위에 만든 book을받아 작동한다
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
        int checkNam = sc.nextInt(); //사용자가 작성한 숫자 변수에 저장

        sc.nextLine(); // nextInt()는 엔터를 남겨두어 다음에 nextLine쓸경우
        // nextInt후 nextLine 사용하여 남은 엔터를 제거한다

        switch (checkNam) { //변수에 따른 case 작동
            case 1: {
                System.out.println(bookController.getBooks());
                //전체조회로 book이 저장되어있는 books리스트 값을 들고와 보여주기

                /* 개선될거 (이렇게하면 하나씩 println되어서 보기 좋을수도있음
                // bookController에서 books 리스트를 가져와서
                // forEach로 하나씩 꺼내어 println으로 출력
                // :: (메서드 참조 연산자)를 사용하여 println 메서드를 직접 전달
                bookController.getBooks().forEach(System.out::println);
                 */
                break; // break가없으면 case1일 실행되고 break가 있는곳까지 계속
                //작동됌
            }
            case 2: {
                System.out.println("정보 조회할 책 이름을 적어주세요");
                String checkName = sc.nextLine(); //조회할 책이름 변수에 저장
                //
                List<Book> nameResult = bookController.isCheckName(checkName);
                //조회할 책이름을 가져가서 확인후 일치하는 값을 가져온거를 변수에 저장
                if (nameResult.isEmpty()) { // 리스트 일경우 변수에 값이없으면 true
                    System.out.println("책 정보 없음");
                } else {
                    System.out.println(nameResult);
                    // nameResult.forEach(System.out::println); 이렇게 사용가능
                    //값이 있을경우 값 보여줌
                }
                break;
            }
            default:
                System.out.println("알맞은 숫자를 입력하세요");

        }


    }
   // 고유번호로 책 수정 메소드
    public static void etaBookProcess(Scanner sc) {
        System.out.println("수정하고싶은 책에 고유 번호를 입력해주세요");
        int bookId = sc.nextInt();
        //사용자가 작성한 고유번호 변수에 저장
        sc.nextLine(); // 엔터 제거

        //변수를 가져가서 비교 후 나온 값을 변수에 저장
        Book checkId = bookController.isCheckId(bookId);
        if (checkId == null) { //가져온값이 null일떄
            System.out.println("알맞지 않은 고유번호입니다,");
        } else { //일치했을떄
            System.out.println("확인되었습니다");
            System.out.println("수정할 책이름을 적어주세요");
            String editBookName = sc.nextLine();
            System.out.println("수정할 작가이름 적어주세요");
            String editAuthor = sc.nextLine();
            System.out.println("수정할 출판사이름을 적어주세요");
            String editPublisher = sc.nextLine();

            //전에받은 id +  지금 받은 수정 책이름,작가 , 출판사를 매개변수로 가져감)
            Book editBook = bookController.isEditBook(bookId, editBookName, editAuthor, editPublisher);
            //
            System.out.println("수정 되었습니다.");
            System.out.println(editBook); //변경된 값을 보여줌
        }


    }
//책이름으로 수정 + 동일 책이름이있을경우 고유번호로 안내
    public static void editBookNameProcess(Scanner sc) {
        System.out.println("수정하고 싶은 책 이름을 적어주세요");
        String checkName = sc.nextLine();
        //사용자 값 저장
        List<Book> nameResult = bookController.isCheckName(checkName);
        //대조후 값을 변수에 저장
        if (nameResult.isEmpty()) { //대조후 값에 값이없으면
            System.out.println("책 정보 없음");
        } else { //값이있으면
            System.out.println(nameResult); //대조된값 보여주기
            if (nameResult.size() > 1) { //대조된값에 객체가 1개 이상일떄
                System.out.println("같은 책이있어 고유번호 입력 부탁드립니다.");
                int bookId = sc.nextInt(); //대조된값에서 보여준 고유번호 입력
                //및 변수에 저장
                sc.nextLine(); // 엔터 삭제
                // 사용자가 준 번호로 고유번호 대조 및 반환값 저장
                Book checkId = bookController.isCheckId(bookId);
                if (checkId == null) { // 값이 없을떄
                    System.out.println("알맞지 않은 고유번호입니다,");
                } else { // 값이있을떄
                    System.out.println("확인되었습니다");
                    System.out.println("수정할 책이름을 적어주세요");
                    String editBookName = sc.nextLine();
                    System.out.println("수정할 작가이름 적어주세요");
                    String editAuthor = sc.nextLine();
                    System.out.println("수정할 출판사이름을 적어주세요");
                    String editPublisher = sc.nextLine();
                    //int editId = checkId.getBookId();
                    // bookId인증된 후라서 그대로 써도됌
                    Book editBook = bookController.isEditBook(bookId, editBookName, editAuthor, editPublisher);
                    System.out.println("수정 되었습니다.");
                    System.out.println(editBook);
                }
            } else { // 검색 결과가 1개일떄 물어볼 필요없음
                System.out.println("확인되었습니다");
                System.out.println("수정할 책이름을 적어주세요");
                String editBookName = sc.nextLine();
                System.out.println("수정할 작가이름 적어주세요");
                String editAuthor = sc.nextLine();
                System.out.println("수정할 출판사이름을 적어주세요");
                String editPublisher = sc.nextLine();
                //int bookId = nameResult.get(0).getBookId();
                //리스트라서 .get(0) (인덱스 0)을 고름
                //인덱스 0번쨰 .getBookId(); id를 변수에 저장
                //Book 객체값으로 가져와서 int bookId를 가져올수있음
                //그러면 isEditBookName을 안만들고 isEditBook으로 수정가능
                Book editBook = bookController.isEditBookName(nameResult, editBookName, editAuthor, editPublisher);
                //함수로 매개변수 가져가서 책이름 대조후 수정한후 반환값을 변수에 저장
                System.out.println("수정 되었습니다.");
                System.out.println(editBook); //반환값 저장된 변수 출력

            }

        }


    }
    /*
    public static void editAndPrint(Scanner sc, int bookId) {
    System.out.println("확인되었습니다");
    System.out.println("수정할 책이름을 적어주세요");
    String editBookName = sc.nextLine();
    System.out.println("수정할 작가이름 적어주세요");
    String editAuthor = sc.nextLine();
    System.out.println("수정할 출판사이름을 적어주세요");
    String editPublisher = sc.nextLine();

    Book editBook = bookController.isEditBook(bookId, editBookName, editAuthor, editPublisher);
    System.out.println("수정 되었습니다.");
    System.out.println(editBook);
}   를 만들어서 함수로 사용했으면 더 짧고 편하게 작업가능했고
  고유번호 및 책제목으로 수정 하지말고
  책제목 하나만 하는게 더 깔금하고 편리함 //isEditBookName 은 필요없었음
     */

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
