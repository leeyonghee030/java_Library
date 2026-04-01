package org.example.controller;


import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {
private List<User> users = new ArrayList<>();
//User만 담을수있는 List를 users로 생성하고 그안에 ArrayList를 넣음
    //실제 상자=ArrayList 규격은=User
private int nextId = 1;

    public boolean addUser(User u) {
        u.setUserId(nextId++);
        //BookID 자동으로 생성을 위해 nextId++를 사용하여 한번 작동후
        // 자동 숫자를 올라가도록 만듬
        users.add(u);
       return true;
    }

        //생성
    public boolean addNameCheck(String n){
        String pattern ="^[a-zA-Z0-9]{3,10}$";
        // 패턴 분해
        //```
        //^           = 문자열 시작
        //[a-zA-Z0-9] = 영문자(대소문자) + 숫자만 허용
        //{3,10}      = 최소 3글자 ~ 최대 10글자
        //$           = 문자열 끝
        if (n.matches(pattern)){
            //matches()는 문자열이 패턴과 일치하는지 확인하는 메소드
            return true;
        }else {
            return false;
        }
    }

    public boolean addUserNameCheck(String n){
        String userNamePattern = "^[a-zA-Z0-9]{6,12}$";
        if (n.matches(userNamePattern)){
            return true;
        }else {
            return false;
        }
    }

    public  boolean checkUsernameDuplicate(String n) {
        for (User u : users) {
            if (u.getUserName().equals(n)) {
                return false;//users안에 UserName 비교해서 대조하는게있으면
                //반환값 false로
            }
            // 반복문 다돌기위해
        }return true;
    }




        //조회
   public User gerUserById(int id){
        return null;
   }

        //수정

        //삭제
     public boolean deleteUserById(int id){
        return  true;
     }
    }
